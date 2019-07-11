package engine;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MSystem {
	// region project information
	public String name = "";
	public String designer = "";
	public String process = "";
	public final Date date;
	// endregion

	// region streams
	// 原水
	public MStream streams;
	// 进水
	public MStream streamf;
	// 浓水
	public MStream streamc;
	// 浓水循环
	public MStream streamr = null;
	// 产水
	public MStream streamp;
	// endregion

	private List<MSection> sections = new ArrayList<>();

	// region initial parameters
	// 设计产水量 m3/h
	public double pariQp = 0;
	// 设计回收率 %
	public double pariY = 0;
	// 设计回流量 m3/h
	public double pariQr = 0;
	// 储存P用户输入的值
	public double temp = 0.0;
	
	//功率之和
	private double sumparW=0;
	//电费单元成本 元/kWh
	public double e=0.62;
	
		
	// 设计平均通量 LMH
	public double pariJ() throws Exception {
		double j = 0;
		for (MSection sec : sections) {
			j += sec.parNVi * sec.parEi * new MBrane(sec.model).parSE;
		}
		j = pariQp / j * 1000;
		return j;
	}

	// 设计进水流量 m3/h
	public double pariQf() {
		return pariQp / pariY * 100;
	}

	// 设计浓水流量 m3/h
	public double pariQc() {
		return pariQp / pariY * (100 - pariY);
	}

	// 设计膜回收率 %
	public double pariYD() {
		return pariQp / (pariQf() + pariQr) * 100;
	}
	// endregion

	public MSystem() throws Exception {
		date = new Date();
		streams = new MStream();
		streams.ion(EIon.HCO3).parcj(0.10);
		section(2);
		MPumpmode();
	}

	// region section management
	public int section() {
		return sections.size();
	}

	public void section(int count) {
		while (sections.size() < count) {
			sections.add(new MSection());
		}
		while (sections.size() > count) {
			sections.remove(sections.size() - 1);
		}
	}

	public MSection[] sections() {
		return sections.toArray(new MSection[0]);
	}
	// endregion

	//泵
	private List<MPump> pumps = new ArrayList<>();
	public void MPumpmode() {
		pumps.add(new MPump("低压泵"));
		pumps.add(new MPump("高压泵"));
		pumps.add(new MPump("增压泵"));
		pumps.add(new MPump("回流泵"));
	}
	public MPump[] pumps() {
		return this.pumps.toArray(new MPump[0]);
	}
	public double parη(int i){
		return 3.59*this.pumps()[i].parη_PJ*this.pumps()[i].parη_MJ*this.pumps()[i].parη_VFD;
	}
	
	// 用电量占比
	private double parX(double w){
		return w/this.sumparW;
	}
	//系统吨水电耗，kWh/d
	public double PT(){
		return this.sumparW/streamp.parQ;
	}
	//系统电耗，kWh/m3
	public double P(){
		return this.sumparW*24;
	}
	//电费成本，元/天
	public double E(){
		return P()*e;
	}
	
	
	public void calculate() throws Exception {
		double parqd = Math.abs((streams.parqC() - streams.parqA()) / streams.parqC());
		if (parqd > 0.1) {
			throw new Exception("阴阳离子不平衡超过10%，请调整离子浓度或以Na或Cl离子平衡。");
		}
		streams.parP = 0.25;
		streams.parQ = pariQf();
		streams.ion(EIon.PO4).parmj(streams.mj_PO4(temp));
		streams.ion(EIon.HPO4).parmj(streams.mj_HPO4(temp));
		streams.ion(EIon.H2PO4).parmj(streams.mj_H2PO4(temp));
		streams.ion(EIon.P).parmj(0);
		streamr = null;
		double lmSO4 = 0;
		double parw,tempsum;
		long start = new Date().getTime();
		while (true) {
			double a = 0.0, b = 0.0, c = 0.0;
			parw=0.0;tempsum=0.0;
			if (streamr == null) {
				streamf = streams.copy();
				streamf.parQ = this.pariQf() + this.pariQr;
			} else {
				streamf = MStream.mix(streams, streamr);
				streamf.parP = streams.parP;
			}

			for (int i = 0; i < sections.size(); i++) {
				MSection section = sections.get(i);
				if (i == 0) {
					section.streamf = this.streamf.copy();
					section.parDpi = 0;
					section.parPLi = 0;
					section.calculate(streams.parmH2CO3());
					this.streamp = section.streamp.copy();
				} else {
					section.streamf = sections.get(i - 1).streamc.copy();
					section.streamf.parP = sections.get(i - 1).streamc.parP - 0.015;
					section.calculate(streams.parmH2CO3());
					this.streamp = MStream.mix(this.streamp, section.streamp);
				}
				this.streamc = section.streamc.copy();
				a = a + section.streamp.ion(EIon.PO4).ioncp_p;
				b = b + section.streamp.ion(EIon.HPO4).ioncp_p;
				c = c + section.streamp.ion(EIon.H2PO4).ioncp_p;
				if(sections.get(i).parDpi > 0){
					parw+=(sections.get(i).streamf.parP-sections.get(i - 1).streamc.parP)*sections.get(i - 1).streamc.parQ;
				}else {
					parw=0;
				}
			}
			this.streamp.ion(EIon.PO4).cp_p = a / this.streamp.parQ;
			this.streamp.ion(EIon.HPO4).cp_p = b / this.streamp.parQ;
			this.streamp.ion(EIon.H2PO4).cp_p = c / this.streamp.parQ;
			this.streamp.ion(EIon.P).cp_p = (this.streamp.ion(EIon.PO4).cp_p / this.streamp.ion(EIon.PO4).parMj
					+ this.streamp.ion(EIon.HPO4).cp_p / this.streamp.ion(EIon.HPO4).parMj
					+ this.streamp.ion(EIon.H2PO4).cp_p / this.streamp.ion(EIon.H2PO4).parMj) * 30.974;
			this.streamp.updpH(streams.parmH2CO3());
			// region step-4
			if (this.streamp.parQ > 1.01 * pariQp) {
				this.streams.parP -= 0.001;
			} else if (this.streamp.parQ < 0.99 * pariQp) {
				this.streams.parP += 0.001;
			} else if (pariQr > 0) {
				// region step-5
				if (this.streamr == null || (streamr != null && lmSO4 != 0
						&& Math.abs((this.streamc.ion(EIon.SO4).parmj() - lmSO4) / lmSO4) > 0.000001)) {
					this.streamr = this.streamc.copy();
					this.streamr.parQ = pariQr;
				} else if (this.streamp.parQ > 1.01 * pariQp) {
					this.streams.parP -= 0.001;
				} else if (this.streamp.parQ < 0.99 * pariQp) {
					this.streams.parP += 0.001;
				} else {
					break;
				}
				// endregion
			} else {
				break;
			}
			if (new Date().getTime() - start > 10000) {
				throw new TimeoutException("系统模型计算超时");
			}
			// endregion
			lmSO4 = this.streamc.ion(EIon.SO4).parmj();
		}
		this.pumps()[0].parW=this.pumps()[0].parP*streams.parQ/parη(0);
		this.pumps()[1].parW=(sections.get(0).streamf.parP-this.pumps()[0].parP)*sections.get(0).streamf.parQ/parη(1);
		this.pumps()[2].parW=parw/parη(2);
		if(pariQr>0&&(this.pumps()[0].parP-this.streamc.parP)>0){
			this.pumps()[3].parW=(this.pumps()[0].parP-this.streamc.parP)*pariQr/parη(3);
		}else{
			this.pumps()[3].parW=0;
		}
		for(int i=0;i<pumps().length;i++){
			tempsum+=this.pumps()[i].parW;
		 }
		this.sumparW=tempsum;
		for(int i=0;i<pumps().length;i++){
			this.pumps()[i].parX=parX(this.pumps()[i].parW);
		 }
	}
}
