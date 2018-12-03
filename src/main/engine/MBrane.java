package engine;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeoutException;

public class MBrane {
	// region basic features
	// 膜直径 inch
	public double width;
	// 膜长度 inch
	public double length;
	// 膜面积 m2
	public double area;

	// 水力透过系数
	public double parA;
	//
	public double parw;
	//
	private double park;
	//
	private double park0;
	//
	private double parx0u;
	// 膜元件面积 m2
	public double parSE;
	// 浓水流道截面积 m2
	private double parAE;

	// COD截留率
	private double damCOD;
	// <=42截留率
	private double damMin;
	// 截留率系数
	private double damCoe;
	// 截留率除数
	private double damDiv;
	// >=400截留率
	private double damMax;
	// endregion

	// region streams
	// 进水
	public MStream streamf;
	// 浓水
	public MStream streamc;
	// 产水
	public MStream streamp;
	// 产水背压 MPa
	public double parPpi;
	// endregion

	Dictionary<EIon, MBIon> ipass = new Hashtable<>();

	// region constant calculation
	// 温度修正因子
	private double parTCF() {
		return Math.exp(parw * (streamf.parT() - 25));
	}

	// 进水SO4离子强度占比
	private double parXuSO4() {
		double xu = 0;
		for (EIon ion : EIon.values()) {
			xu += Math.pow(streamf.ion(ion).parZj, 2) * streamf.ion(ion).parmj();
		}
		xu = 4 * streamf.ion(EIon.SO4).parmj() / xu;
		return xu;
	}

	// 浓水压降 MPa
	private double parDPfc() {
		return (park * Math.pow(streamf.parQ - parJp * parSE / 1000 / 2, 2) + park0) / 1000;
	}

	// 平均渗透压
	private double parpifc() {
		double fc = 0;
		for (EIon ion : EIon.values()) {
			fc += parBj(ion) * (1 + (1 - parYt() * (1 - parRjd(ion))) / (1 - parYt())) * streamf.ion(ion).parmj();
		}
		fc = 8.314 / 1000 * (273.15 + streamf.parT()) / 2 * fc;
		return fc;
	}

	// 产水渗透压
	private double parpip() {
		double p = 0;
		for (EIon ion : EIon.values()) {
			p += parBj(ion) * parTrjd(ion) * streamf.ion(ion).parmj();
		}
		p = 8.314 / 1000 * (273.15 + streamf.parT()) * p;
		return p;
	}

	// 水通量
	private boolean firstJp = true;
	private double parJp = 0;

	private double parJp() {
		if (firstJp) {
			firstJp = false;
			parJp = parA * parTCF() * (streamf.parP - parPpi);
		} else {
			parJp = parA * parTCF() * (streamf.parP - parDPfc() / 2 - parPpi - (parpifc() - parpip()));
		}
		return parJp;
	}

	// 回收率
	public double parYt() {
		return parJp * parSE / 1000 / streamf.parQ;
	}

	// 离子透过率
	private double parTrj(EIon ion) {
		return 1 / (2 * (1 - ipass.get(ion).Pj) / (ipass.get(ion).Pj + 2 * ipass.get(ion).Ds / parJp) + 1);
	}

	// 离子截留率
	private double parRj(EIon ion) {
		return 1 - parTrj(ion);
	}

	// 修正离子透过率
	private double parRjd(EIon ion) {
		return parRj(ion) + ipass.get(ion).qj * (parXuSO4() - parx0u);
	}

	private double parTrjd(EIon ion) {
		return 1 - parRjd(ion);
	}

	// 膜面流速
	private double parut() {
		return streamf.parQ * 1000 * (1 - parYt() / 2) / parAE;
	}

	// 浓差极化系数
	private double parBj(EIon ion) {
		return Math.exp(ipass.get(ion).bj * parJp / parut())
				/ (parRjd(ion) + (1 - parRjd(ion)) * Math.exp(ipass.get(ion).bj * parJp / parut()));
	}

	// COD透过系数
	private double parRCOD(double Mj) {
		if (Mj == 0) {
			return damCOD;
		}
		if (Mj <= 42) {
			return damMin;
		}
		if (Mj > 400) {
			return damMax;
		}
		return 1 - damCoe * Math.exp(-Mj / damDiv);
	}

	// 通量 LMH
	public double parFi() {
		return this.streamp.parQ * 1000 / this.parSE;
	}
	// endregion

	public MBrane(String name) throws Exception {
		if (name.equals("DF30-8040(365)")) {
			this.width = 8;
			this.length = 40;
			this.area = 34;
			this.parA = 82.5;
			this.parw = 0.03488;
			this.park = 0.4853;
			this.park0 = 4.2;
			this.parx0u = 0.1245;
			this.parSE = 34;
			this.parAE = 0.0174;
			ipass.put(EIon.K, new MBIon(0.6244, 25.7754, 0, 1.1609));
			ipass.put(EIon.Na, new MBIon(0.5412, 34.1303, 0, 1.1609));
			ipass.put(EIon.NH4, new MBIon(0.6247, 7.8787, 0, 1.1609));
			ipass.put(EIon.Ca, new MBIon(0.6519, 2.7940, 0, 1.4391));
			ipass.put(EIon.Mg, new MBIon(0.6321, 2.4276, 0, 1.4391));
			ipass.put(EIon.Ba, new MBIon(0.6520, 2.7940, 0, 1.4391));
			ipass.put(EIon.Sr, new MBIon(0.6519, 2.7940, 0, 1.4391));
			ipass.put(EIon.Fe2, new MBIon(0.66, 2.4, 0, 0));
			ipass.put(EIon.Mn, new MBIon(0.65, 2.02, 0, 0));
			ipass.put(EIon.Fe3, new MBIon(0.03, 0.6, 0, 0));
			ipass.put(EIon.Al, new MBIon(0.04, 0.65, 0, 0));
			ipass.put(EIon.NO3, new MBIon(1.0507, 1.5927, 0, -1.0789));
			ipass.put(EIon.F, new MBIon(0.3965, 23.8206, 0, 0));
			ipass.put(EIon.Cl, new MBIon(1.0039, 9.7, 0, -0.5));
			ipass.put(EIon.HCO3, new MBIon(0.5273, 20.3184, 0, 0.065));
			ipass.put(EIon.SO4, new MBIon(0.0415, 0.6144, 0, 0));
			ipass.put(EIon.PO4, new MBIon(0.0366, 0.5227, 0, -0.1107));
			this.damCOD = 0.7;
			this.damMin = 0.05;
			this.damCoe = 1.401;
			this.damDiv = 110.44;
			this.damMax = 0.95;
		} else if (name.equals("DF30-8040(400)")) {
			this.width = 8;
			this.length = 40;
			this.area = 37;
			this.parA = 92;
			this.parw = 0.03488;
			this.park = 0.3802;
			this.park0 = 4.0815;
			this.parx0u = 0.1245;
			this.parSE = 37;
			this.parAE = 0.0159;
			ipass.put(EIon.K, new MBIon(0.6244, 25.7754, 0, 1.1609));
			ipass.put(EIon.Na, new MBIon(0.5412, 34.1303, 0, 1.1609));
			ipass.put(EIon.NH4, new MBIon(0.6247, 7.8787, 0, 1.1609));
			ipass.put(EIon.Ca, new MBIon(0.6519, 2.7940, 0, 1.4391));
			ipass.put(EIon.Mg, new MBIon(0.6321, 2.4276, 0, 1.4391));
			ipass.put(EIon.Ba, new MBIon(0.6520, 2.7940, 0, 1.4391));
			ipass.put(EIon.Sr, new MBIon(0.6519, 2.7940, 0, 1.4391));
			ipass.put(EIon.Fe2, new MBIon(0.66, 2.4, 0, 0));
			ipass.put(EIon.Mn, new MBIon(0.65, 2.02, 0, 0));
			ipass.put(EIon.Fe3, new MBIon(0.03, 0.6, 0, 0));
			ipass.put(EIon.Al, new MBIon(0.04, 0.65, 0, 0));
			ipass.put(EIon.NO3, new MBIon(1.0507, 1.5927, 0, -1.0789));
			ipass.put(EIon.F, new MBIon(0.3965, 23.8206, 0, 0));
			ipass.put(EIon.Cl, new MBIon(1.0039, 9.7, 0, -0.5));
			ipass.put(EIon.HCO3, new MBIon(0.5273, 20.3184, 0, 0.065));
			ipass.put(EIon.SO4, new MBIon(0.0415, 0.6144, 0, 0));
			ipass.put(EIon.PO4, new MBIon(0.0366, 0.5227, 0, -0.1107));
			this.damCOD = 0.7;
			this.damMin = 0.05;
			this.damCoe = 1.401;
			this.damDiv = 110.44;
			this.damMax = 0.95;
		} else if (name.equals("DF301-8040(400)")) {
			this.width = 8;
			this.length = 40;
			this.area = 37;
			this.parA = 85.7;
			this.parw = 0.03456;
			this.park = 0.4772;
			this.park0 = 5.0093;
			this.parx0u = 0.2325;
			this.parSE = 37;
			this.parAE = 0.0164;
			ipass.put(EIon.K, new MBIon(0.8554, 3.9042, 0, 1.4708));
			ipass.put(EIon.Na, new MBIon(0.8410, 4.8186, 0, 1.4708));
			ipass.put(EIon.NH4, new MBIon(0.8309, 5.8536, 0, 1.4708));
			ipass.put(EIon.Ca, new MBIon(0.6268, 4.0734, 1547.1, 1.7789));
			ipass.put(EIon.Mg, new MBIon(0.6425, 3.8844, 1547.1, 1.7789));
			ipass.put(EIon.Ba, new MBIon(0.7544, 2.2086, 1547.1, 1.7789));
			ipass.put(EIon.Sr, new MBIon(0.6425, 3.2922, 1547.1, 1.7789));
			ipass.put(EIon.Fe2, new MBIon(0.63, 4, 1547.1, 0));
			ipass.put(EIon.Mn, new MBIon(0.62, 3.8, 1547.1, 0));
			ipass.put(EIon.Fe3, new MBIon(0.07, 1.5, 1547.1, 0));
			ipass.put(EIon.Al, new MBIon(0.05, 2, 1547.1, 0));
			ipass.put(EIon.NO3, new MBIon(1.0683, 2.3446, 0, -1.3934));
			ipass.put(EIon.F, new MBIon(0.9, 2, 0, 0));
			ipass.put(EIon.Cl, new MBIon(1.0659, 2.1798, 0, -1.2));
			ipass.put(EIon.HCO3, new MBIon(0.9662, 2.0340, 0, -1.0221));
			ipass.put(EIon.SO4, new MBIon(0.0242, 1.0780, 3606.7, 0));
			ipass.put(EIon.PO4, new MBIon(0.0549, 2.0916, 3606.7, 0.4300));
			this.damCOD = 0.6;
			this.damMin = 0.05;
			this.damCoe = 1.3;
			this.damDiv = 160;
			this.damMax = 0.9;
		} else {
			throw new Exception("unsupported membrane model");
		}
	}

	public void calculate(double mfH2CO3) throws Exception {
		int count = 0;
		firstJp = true;
		double Jp = parJp();
		while (true) {
			if (Jp > 0) {
				double Jpn = parJp();
				MLogger.memlog(String.format("Jp %f -> %f, %f%%", Jp, Jpn, 100 * Math.abs((Jpn - Jp) / Jp)));
				if (Jpn == Double.POSITIVE_INFINITY || Jpn == Double.NaN) {
					throw new ArithmeticException("元件水通量计算错误");
				}
				Jp = Jpn;
				count++;
			} else {
				Jp = 0;
				break;
			}
			if (count == 20) {
				break;
			}
		}

		streamp = streamf.copy();
		streamp.parQ = parJp * parSE / 1000;
		streamc = streamf.copy();
		streamc.parQ = streamf.parQ - streamp.parQ;
		for (EIon ion : EIon.values()) {
			streamp.ion(ion).parcj(parBj(ion) * parTrjd(ion) * streamf.ion(ion).parcj());
			streamc.ion(ion)
					.parcj((1 - parYt() * parBj(ion) * parTrjd(ion)) / (1 - parYt()) * streamf.ion(ion).parcj());
		}
		for (MCOD c : streamf.cods()) {
			streamp.cod(c.name).parcj = (1 - parRCOD(c.parMj)) * c.parcj;
			streamc.cod(c.name).parcj = (1 - parYt() * (1 - parRCOD(c.parMj))) / (1 - parYt()) * c.parcj;
		}
		streamp.parP = parPpi;
		streamc.parP = streamf.parP - parDPfc();
		streamc.updpH(mfH2CO3);
	}
}
