package engine;

import java.util.*;

public class MStream {
	// region inherent features

	// 离子一级水解常数
	private double Kb1 = 2.27 * Math.pow(10, -2);
	// 离子二级水解常数
	private double Kb2 = 1.59 * Math.pow(10, -7);
	// 离子三级水解常数
	private double Kb3 = 1.32 * Math.pow(10, -12);

	// 流量 m3/h
	public double parQ = 0;

	// 压力 MPa
	public double parP = 0;

	// 温度
	private double parT = 25;

	public double parT() {
		return this.parT;
	}

	public void parT(double value) throws Exception {
		if (value < 3 || value > 40) {
			throw new Exception("输入温度不在有效范围内[3~40]。");
		}
		this.parT = value;
	}

	// pH
	private double parpH = 7;

	public double parpH() {
		return this.parpH;
	}

	public void parpH(double value) throws Exception {
		if (value < 2 || value > 12) {
			throw new Exception("输入pH不在有效范围内[2~12]。");
		}
		this.parpH = value;
	}

	private double parKH2CO3() {
		return Math.pow(10, -6.3) * Math.exp(-7.61 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	public double parmH2CO3() {
		return ion(EIon.HCO3).parmj() * Math.pow(10, -parpH) / parKH2CO3();
	}

	public void updpH(double mfH2CO3) {
		this.parpH = -Math.log10(mfH2CO3 * parKH2CO3() / ion(EIon.HCO3).parmj());
	}

	public double parqA() {
		double qa = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			MIon ion = values.nextElement();
			if (ion.parZj < 0) {
				qa += ion.parzj();
			}
		}
		return qa;
	}

	public double parqC() {
		double qc = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			MIon ion = values.nextElement();
			if (ion.parZj > 0) {
				qc += ion.parzj();
			}
		}
		return qc;
	}

	// TDS
	public double tds() {
		double tds = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			tds += values.nextElement().parcj();
		}
		return tds;
	}

	public void tds(double value) throws Exception {
		if (tds() > value) {
			throw new Exception("输入TDS与离子浓度不匹配，请核实。");
		}
		double qd = parqC() - parqA();
		if (qd > 0) {
			ion(EIon.Cl).parzj(ion(EIon.Cl).parzj() + Math.abs(qd));
		} else {
			ion(EIon.Na).parzj(ion(EIon.Na).parzj() + Math.abs(qd));
		}
		double tdsd = value - tds();
		double tmcd = tdsd / (ion(EIon.Na).parMj + ion(EIon.Cl).parMj) / 1000;
		ion(EIon.Na).parmj(ion(EIon.Na).parmj() + tmcd);
		ion(EIon.Cl).parmj(ion(EIon.Cl).parmj() + tmcd);
	}

	// 碱度 mg/L
	public double parcjd() {
		double mhco3 = ion(EIon.HCO3).parcj() / 61.01 / 1000;
		double malka = mhco3 * (1 + 2 * parKHCO3() / Math.pow(10, -parpH));
		double calka = malka * 50.05 * 1000;
		return calka;
	}

	public void parcjd(double value) {
		double malka = value / 50.05 / 1000;
		double mhco3 = malka / (1 + 2 * parKHCO3() / Math.pow(10, -parpH));
		ion(EIon.HCO3).parcj(mhco3 * 61.01 * 1000);
	}

	// 电导率 us/cm
	public double parS() {
		double s = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			MIon ion = values.nextElement();
			s += ion.parmj() * ion.parSj * Math.pow(10, 7);
		}
		s = s + Math.pow(10, -parpH) * 0.034982 * Math.pow(10, 7);
		return s;
	}

	// 离子强度 u
	public double paru() {
		double u = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			MIon ion = values.nextElement();
			u += ion.parmj() * Math.pow(ion.parZj, 2);
		}
		u = u / 2;
		return u;
	}

	// 渗透压
	public double parpif() {
		double pif = 0;
		Enumeration<MIon> values = ions.elements();
		while (values.hasMoreElements()) {
			pif += values.nextElement().parmj();
		}
		pif = 8.314 / 1000 * (273.15 + parT) * pif;
		return pif;
	}
	// endregion

	// region constant calculation
	private double parKHCO3() {
		return Math.pow(10, -10.3) * Math.exp(-3.55 * 4184 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKCaCO3() {
		return Math.pow(10, -8.3) * Math.exp(2.95 * 4184 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKCaSO4() {
		return Math.pow(10, -4.59) * Math.exp(15.97 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKH3PO4() {
		return Math.pow(10, -2.1) * Math.exp(13 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKH2PO4() {
		return Math.pow(10, -7.2) * Math.exp(-3.8 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKHPO4() {
		return Math.pow(10, -12.3) * Math.exp(-21.3 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parmPO43(double mj_p) {
		return mj_p * parKH3PO4() * parKH2PO4() * parKHPO4()
				/ (Math.pow(Math.pow(10, -parpH), 3) + Math.pow(Math.pow(10, -parpH), 2) * parKH3PO4()
						+ Math.pow(10, -parpH) * parKH3PO4() * parKH2PO4() + parKH3PO4() * parKH2PO4() * parKHPO4());
	}

	private double parKCa3PO42() {
		return Math.pow(10, -26) * Math.exp(51.67 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKBaSO4() {
		return Math.pow(10, -10) * Math.exp(-28.05 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKSrSO4() {
		return Math.pow(10, -6.49) * Math.exp(0.21 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}

	private double parKCaF2() {
		return Math.pow(10, -10.57) * Math.exp(-19.84 * 1000 / 8.314 * (1 / (273.15 + parT) - 1 / 298.15));
	}
	// endregion

	// region saturation calculation
	public double parSCaCO3() {
		return parpH - (-Math.log10(ion(EIon.Ca).parmj()) - Math.log10(ion(EIon.HCO3).parmj()) - Math.log10(parKHCO3())
				+ Math.log10(parKCaCO3()));
	}

	public double parSCaSO4() {
		return ion(EIon.Ca).parmj() * ion(EIon.SO4).parmj() / parKCaSO4();
	}

	public double parSCa3PO42(double mj_p) {
		return Math.pow(ion(EIon.Ca).parmj(), 3) * Math.pow(parmPO43(mj_p), 2) / parKCa3PO42();
	}

	public double parSBaSO4() {
		return ion(EIon.Ba).parmj() * ion(EIon.SO4).parmj() / parKBaSO4();
	}

	public double parSSrSO4() {
		return ion(EIon.Sr).parmj() * ion(EIon.SO4).parmj() / parKSrSO4();
	}

	public double parSCaF2() {
		return ion(EIon.Ca).parmj() * Math.pow(ion(EIon.F).parmj(), 2) / parKCaF2();
	}
	// endregion

	// new model DF304I-8040（400） parameter
	private double parmj_OH() {
		return Math.pow(10, parpH() - 14);
	}

	private double cc() {
		return Math.pow(parmj_OH(), 3) + Math.pow(parmj_OH(), 2) * Kb1 + parmj_OH() * Kb1 * Kb2 + Kb1 * Kb2 * Kb3;
	}

	public double mj_PO4(double mj_p) {
		return Math.pow(parmj_OH(), 3) * mj_p / cc();
	}

	public double mj_HPO4(double mj_p) {
		return Math.pow(parmj_OH(), 2) * mj_p * Kb1 / cc();
	}

	public double mj_H2PO4(double mj_p) {
		return parmj_OH() * mj_p * Kb1 * Kb2 / cc();
	}

	// endregion
	private Dictionary<EIon, MIon> ions = new Hashtable<>();
	private List<MCOD> cods = new ArrayList<>();

	public MStream() throws Exception {
		for (EIon ion : EIon.values()) {
			ions.put(ion, new MIon(ion));
		}
		codmode();
	}

	public void codmode() {
		cods.clear();
		cods.add(new MCOD("COD"));
		cods.add(new MCOD("TOC"));
		cods.add(new MCOD("其它物质"));
	}

	public MIon ion(EIon ion) {
		return ions.get(ion);
	}

	public MCOD cod(String name) {
		for (MCOD cod : cods) {
			if (cod.name.equals(name)) {
				return cod;
			}
		}
		return null;
	}

	public MCOD[] cods() {
		return this.cods.toArray(new MCOD[0]);
	}

	public MStream copy() throws Exception {
		MStream stream = new MStream();
		stream.parT = this.parT;
		stream.parpH = this.parpH;
		stream.parP = this.parP;
		stream.parQ = this.parQ;
		Enumeration<MIon> ei = ions.elements();
		while (ei.hasMoreElements()) {
			MIon ion = ei.nextElement();
			stream.ion(ion.type).parcj(ion.parcj());
		}
		stream.cods.clear();
		for (MCOD cod : this.cods) {
			stream.cods.add(cod.copy());
		}
		return stream;
	}

	public static MStream mix(MStream s1, MStream s2) throws Exception {
		MStream stream = new MStream();
		stream.parT = s1.parT;
		stream.parpH = s1.parpH;
		stream.parP = Math.min(s1.parP, s1.parP);
		stream.parQ = s1.parQ + s2.parQ;
		for (EIon ion : EIon.values()) {
			stream.ion(ion).parcj((s1.ion(ion).parcj() * s1.parQ + s2.ion(ion).parcj() * s2.parQ) / stream.parQ);
		}
		stream.cods.clear();
		for (MCOD c : s1.cods) {
			MCOD cod = c.copy();
			cod.parcj = (s1.cod(c.name).parcj * s1.parQ + s2.cod(c.name).parcj * s2.parQ) / stream.parQ;
			stream.cods.add(cod);
		}
		return stream;
	}
}
