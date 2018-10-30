package engine;

public class MCOD {
    public final String name;
    // 分子质量
    public double parMj = 0;
    // 质量浓度 mg/L
    public double parcj = 0;

    public MCOD(String name) {
        this.name = name;
    }

    public MCOD copy() {
        MCOD cod = new MCOD(this.name);
        cod.parMj = this.parMj;
        cod.parcj = this.parcj;
        return cod;
    }
}
