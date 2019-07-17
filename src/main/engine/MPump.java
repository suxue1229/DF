package engine;

public class MPump {
	public final String name;
	// 泵效率
	public double parη_PJ = 1;
	// 电机效率
	public double parη_MJ = 0;
	// 变频效率
	public double parη_VFD = 0;
	// 功率 kW
	public double parW = 0;
	
	public MPump(String name) {
		this.name=name;
		if(name.equals("高压泵")){
			this.parη_PJ=0.84;
			this.parη_MJ=0.95;
			this.parη_VFD=0.97;
		}else if(name.equals("增压泵")){
			this.parη_PJ=0.84;
			this.parη_MJ=0.95;
			this.parη_VFD=0.97;
		}
	}

}
