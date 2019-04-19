package engine;

public class MBIon {
	// 基准透过系数
	public double Pj = 0;
	// 离子扩散系数 LMH
	public double Ds = 0;
	// 浓差极化常数 温度修正系数
	public double kb = 0;
	// 浓差极化常数温度修正常数
	public double bb = 0;
	// 硫酸根修正系数
	public double qj = 0;

	public MBIon(double Pj, double Ds, double kb, double bb, double qj) {
		this.Pj = Pj;
		this.Ds = Ds;
		this.kb = kb;
		this.bb = bb;
		this.qj = qj;
	}
}
