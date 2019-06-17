package engine;

public class MCOD {
	public final String name;
	// 截留率
	public double parRCOD = 1;
	// 质量浓度 mg/L
	public double parcj = 0;

	public MCOD(String name) {
		this.name = name;
	}

	public MCOD copy() {
		MCOD cod = new MCOD(this.name);
		cod.parRCOD = this.parRCOD;
		cod.parcj = this.parcj;
		return cod;
	}
}
