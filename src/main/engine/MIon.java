package engine;

public class MIon {
	public final String name;
	public final EIon type;
	public double parMj, parSj, parZj;
	private double parcj = 0;
	// P离子产水浓度
	public double ioncp_p = 0;
	public double cp_p = 0;
	// P离子浓水浓度
	public double ioncc_p = 0;
	public double cc_p = 0;

	public MIon(EIon ion) throws Exception {
		this.type = ion;
		switch (ion) {
		case K:
			this.name = "K+";
			this.parMj = 39.10;
			this.parSj = 0.007352;
			this.parZj = 1;
			break;
		case Na:
			this.name = "Na+";
			this.parMj = 22.99;
			this.parSj = 0.005011;
			this.parZj = 1;
			break;
		case NH4:
			this.name = "NH4(以NH4计)";
			this.parMj = 18.01;
			this.parSj = 0.007340;
			this.parZj = 1;
			break;
		case Ca:
			this.name = "Ca2+";
			this.parMj = 40.08;
			this.parSj = 0.011900;
			this.parZj = 2;
			break;
		case Mg:
			this.name = "Mg2+";
			this.parMj = 24.31;
			this.parSj = 0.010612;
			this.parZj = 2;
			break;
		case Ba:
			this.name = "Ba2+";
			this.parMj = 137.3;
			this.parSj = 0.012728;
			this.parZj = 2;
			break;
		case Sr:
			this.name = "Sr2+";
			this.parMj = 87.62;
			this.parSj = 0.011892;
			this.parZj = 2;
			break;
		case Fe2:
			this.name = "Fe2+";
			this.parMj = 55.85;
			this.parSj = 0.0108;
			this.parZj = 2;
			break;
		case Mn:
			this.name = "Mn2+";
			this.parMj = 54.94;
			this.parSj = 0.012;
			this.parZj = 2;
			break;
		case Fe3:
			this.name = "Fe3+";
			this.parMj = 55.85;
			this.parSj = 0.0162;
			this.parZj = 3;
			break;
		case Al:
			this.name = "Al3+";
			this.parMj = 26.98;
			this.parSj = 0.0162;
			this.parZj = 3;
			break;
		case F:
			this.name = "F-";
			this.parMj = 19.00;
			this.parSj = 0.007630;
			this.parZj = -1;
			break;
		case Cl:
			this.name = "Cl-";
			this.parMj = 35.45;
			this.parSj = 0.007634;
			this.parZj = -1;
			break;
		case NO3:
			this.name = "NO3-(以NO3计)";
			this.parMj = 62.01;
			this.parSj = 0.007144;
			this.parZj = -1;
			break;
		case SO4:
			this.name = "SO42-";
			this.parMj = 96.06;
			this.parSj = 0.015960;
			this.parZj = -2;
			break;
		case HCO3:
			this.name = "HCO3-";
			this.parMj = 61.01;
			this.parSj = 0.004460;
			this.parZj = -1;
			break;
		case P:
			this.name = "总磷";
			this.parMj = 30.974;
			this.parSj = 0.025520;
			this.parZj = -1.5;
			break;
		case PO4:
			this.name = "PO4";
			this.parMj = 94.97;
			this.parSj = 0.025520;
			this.parZj = -3;
			break;
		case HPO4:
			this.name = "HPO4";
			this.parMj = 95.98;
			this.parSj = 0.011400;
			this.parZj = -2;
			break;
		case H2PO4:
			this.name = "H2PO4";
			this.parMj = 96.99;
			this.parSj = 0.003600;
			this.parZj = -1;
			break;
		default:
			throw new Exception("unsupported ion");
		}
	}

	// 质量浓度 mg/L
	public double parcj() {
		return this.parcj;
	}

	public void parcj(double value) {
		this.parcj = value;
	}

	// 摩尔浓度 mol/L
	public double parmj() {
		return this.parcj / this.parMj / 1000;
	}

	public void parmj(double value) {

		this.parcj = value * this.parMj * 1000;

	}

	// 电荷浓度 meq/L
	public double parzj() {
		return parmj() * Math.abs(this.parZj) * 1000;
	}

	public void parzj(double value) {
		parmj(value / Math.abs(this.parZj) / 1000);
	}

}
