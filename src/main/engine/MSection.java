package engine;

import java.util.ArrayList;
import java.util.List;

public class MSection {
	// region basic features
	// 膜元件型号
	public String model = "DF304I-8040(400)";
	// 压力容器数
	public int parNVi = 1;
	// 膜元件数
	public int parEi = 6;
	// 产水背压 MPa
	public double parPpi = 0;
	// 段间增压 MPa
	public double parDpi = 0;
	// 段间压力损失 MPa
	public double parPLi = 0;
	// endregion

	// region streams
	// 进水
	public MStream streamf;
	// 浓水
	public MStream streamc;
	// 产水
	public MStream streamp;
	// endregion

	// 通量 LMH
	public double parFi() throws Exception {
		return this.streamp.parQ * 1000 / parNVi / parEi / new MBrane(this.model).parSE;
	}

	// 最大通量 LMH
	public double parFimax() throws Exception {
		double f = 0;
		for (MBrane b : branes) {
			f = Math.max(f, b.parFi());
		}
		return f;
	}

	private List<MBrane> branes = new ArrayList<>();

	public void calculate(double mfH2CO3) throws Exception {
		double a = 0.0, b = 0.0, c = 0.0;
		this.streamf.parP += this.parDpi - this.parPLi;
		branes.clear();
		for (int i = 0; i < parEi; i++) {
			branes.add(new MBrane(this.model));
		}
		for (int i = 0; i < branes.size(); i++) {
			MBrane brane = branes.get(i);
			brane.parPpi = this.parPpi;
			if (i == 0) {
				brane.streamf = this.streamf.copy();
				brane.streamf.parQ /= this.parNVi;
				brane.calculate(mfH2CO3);
				this.streamp = brane.streamp.copy();
			} else {
				brane.streamf = branes.get(i - 1).streamc.copy();
				brane.calculate(mfH2CO3);
				this.streamp = MStream.mix(this.streamp, brane.streamp);
			}
			this.streamc = brane.streamc.copy();
			MLogger.memlog(String.format("{%d} Q:%f", i + 1, brane.streamp.parQ));
			a = a + brane.streamp.ion(EIon.PO4).ioncp_p;
			b = b + brane.streamp.ion(EIon.HPO4).ioncp_p;
			c = c + brane.streamp.ion(EIon.H2PO4).ioncp_p;
		}
		this.streamp.ion(EIon.PO4).cp_p = a / this.streamp.parQ;
		this.streamp.ion(EIon.HPO4).cp_p = b / this.streamp.parQ;
		this.streamp.ion(EIon.H2PO4).cp_p = c / this.streamp.parQ;
		this.streamp.ion(EIon.P).cp_p = (this.streamp.ion(EIon.PO4).cp_p / this.streamp.ion(EIon.PO4).parMj
				+ this.streamp.ion(EIon.HPO4).cp_p / this.streamp.ion(EIon.HPO4).parMj
				+ this.streamp.ion(EIon.H2PO4).cp_p / this.streamp.ion(EIon.H2PO4).parMj) * 30.974;
		this.streamc.ion(EIon.P).cc_p = (this.streamc.ion(EIon.PO4).parcj() / this.streamp.ion(EIon.PO4).parMj
				+ this.streamc.ion(EIon.HPO4).parcj() / this.streamp.ion(EIon.HPO4).parMj
				+ this.streamc.ion(EIon.H2PO4).parcj() / this.streamp.ion(EIon.H2PO4).parMj) * 30.974;

		this.streamp.ion(EIon.PO4).ioncp_p = this.streamp.ion(EIon.PO4).cp_p * this.streamp.parQ;
		this.streamp.ion(EIon.HPO4).ioncp_p = this.streamp.ion(EIon.HPO4).cp_p * this.streamp.parQ;
		this.streamp.ion(EIon.H2PO4).ioncp_p = this.streamp.ion(EIon.H2PO4).cp_p * this.streamp.parQ;
		this.streamc.parQ *= this.parNVi;
		this.streamc.updpH(mfH2CO3);
		this.streamp.parP = parPpi;
		this.streamp.parQ *= this.parNVi;
		this.streamp.updpH(mfH2CO3);
	}

	public MBrane[] branes() {
		return this.branes.toArray(new MBrane[0]);
	}
}
