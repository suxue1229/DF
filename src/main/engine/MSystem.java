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
		long start = new Date().getTime();
		while (true) {
			double a = 0.0, b = 0.0, c = 0.0;
			if (streamr == null) {
				streamf = streams.copy();
				streamf.parQ = this.pariQf() + this.pariQr;
			} else {
				streamf = MStream.mix(streams, streamr);
				streamf.parP = streams.parP;
			}
			MLogger.syslog(String.format("{INIT} P:%f, Q:%f", streamf.parP, streamf.parQ));

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
				MLogger.seclog(String.format("{%d} Q:%f", i + 1, section.streamp.parQ));

				a = a + section.streamp.ion(EIon.PO4).ioncp_p;
				b = b + section.streamp.ion(EIon.HPO4).ioncp_p;
				c = c + section.streamp.ion(EIon.H2PO4).ioncp_p;
			}
			this.streamp.ion(EIon.PO4).cp_p = a / this.streamp.parQ;
			this.streamp.ion(EIon.HPO4).cp_p = b / this.streamp.parQ;
			this.streamp.ion(EIon.H2PO4).cp_p = c / this.streamp.parQ;
			this.streamp.ion(EIon.P).cp_p = (this.streamp.ion(EIon.PO4).cp_p / this.streamp.ion(EIon.PO4).parMj
					+ this.streamp.ion(EIon.HPO4).cp_p / this.streamp.ion(EIon.HPO4).parMj
					+ this.streamp.ion(EIon.H2PO4).cp_p / this.streamp.ion(EIon.H2PO4).parMj) * 30.974;
			this.streamp.updpH(streams.parmH2CO3());

			MLogger.syslog(String.format("{RESULT} Q:%f", this.streamp.parQ));
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
			MLogger.syslog(String.format("{ASSERT} Q:%f != %f", this.streamp.parQ, pariQp));
			if (new Date().getTime() - start > 10000) {
				throw new TimeoutException("系统模型计算超时");
			}
			// endregion
			lmSO4 = this.streamc.ion(EIon.SO4).parmj();
		}
		File log = new File(System.getProperty("user.dir"), "detail.log");
		if (log.exists()) {
			FileWriter writer = new FileWriter(log, false);
			writer.write("名称：" + this.name + "水质报告  \r\n");
			writer.write("设计：" + this.designer + "  \r\n");
			writer.write("工艺：" + this.process + "  \r\n");
			writer.write("时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n\r\n");
			writer.write("# 系统水质详情：\r\n\r\n");
			renderStream(writer, "- 原水水质：\r\n\r\n", this.streams);
			renderStream(writer, "- 进水水质：\r\n\r\n", this.streamf);
			renderStream(writer, "- 浓水水质：\r\n\r\n", this.streamc);
			renderStream(writer, "- 产水水质：\r\n\r\n", this.streamp);
			for (int i = 0; i < sections.size(); i++) {
				MSection section = sections.get(i);
				writer.write(String.format("# 第 %d 段水质详情：\r\n\r\n", i + 1));
				renderStream(writer, "- 进水水质：\r\n\r\n", section.streamf);
				renderStream(writer, "- 浓水水质：\r\n\r\n", section.streamc);
				renderStream(writer, "- 产水水质：\r\n\r\n", section.streamp);
				for (int j = 0; j < section.branes().length; j++) {
					MBrane brane = section.branes()[j];
					writer.write(String.format("## 第 %d 段，第 %d 支水质详情：\r\n\r\n", i + 1, j + 1));
					renderStream(writer, "- 进水水质：\r\n\r\n", brane.streamf);
					renderStream(writer, "- 浓水水质：\r\n\r\n", brane.streamc);
					renderStream(writer, "- 产水水质：\r\n\r\n", brane.streamp);
				}
			}
			writer.flush();
			writer.close();
		}
	}

	private void renderStream(FileWriter writer, String name, MStream stream) {
		try {
			writer.write(name);
			writer.write(String.format("  | %-8s | %-11s | %-12s | %-12s |\r\n", "指标", "质量浓度", "摩尔浓度", "电荷浓度"));
			writer.write("  |------------|-----------------|------------------|------------------|\r\n");
			for (EIon ion : EIon.values()) {
				writer.write(String.format("  | %-10s | %10.2f mg/L | %10.2f mol/L | %10.2f meq/L |\r\n", ion.name(),
						stream.ion(ion).parcj(), stream.ion(ion).parmj(), stream.ion(ion).parzj()));
			}
			writer.write("\r\n");
		} catch (Exception e) {
		}
	}
}
