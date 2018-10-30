package engine;

public class MBIon {
    // 基准透过系数
    public double Pj = 0;
    // 离子扩散系数 LMH
    public double Ds = 0;
    // 浓差极化常数
    public double bj = 0;
    // 硫酸根修正系数
    public double qj = 0;

    public MBIon() {

    }

    public MBIon(double Pj, double Ds, double bj, double qj) {
        this.Pj = Pj;
        this.Ds = Ds;
        this.bj = bj;
        this.qj = qj;
    }
}
