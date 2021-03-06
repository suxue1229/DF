package engine;

import java.util.ArrayList;
import java.util.List;

public class MSection {
    //region basic features
    // 膜元件型号
    public String model = "DF301-8040(400)";
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
    //endregion

    //region streams
    // 进水
    public MStream streamf;
    // 浓水
    public MStream streamc;
    // 产水
    public MStream streamp;
    //endregion
    
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
    
    public MSection() {
    }
    public void calculate(double mfH2CO3) throws Exception {
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
        }
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
