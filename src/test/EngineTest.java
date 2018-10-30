import engine.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

public class EngineTest {
    @Test
    public void StreamTest() throws Exception {
        double tds = 120;
        MStream stream = new MStream();
        stream.tds(tds);
        System.out.println(String.format("TDS: %6.2f mg/L", stream.tds()));
        System.out.println("Details:");
        for (EIon ion : EIon.values()) {
            MIon mion = stream.ion(ion);
            System.out.println(String.format("  %-4s: %6.2f mg/L, %6.2f mmol/L, %6.2f meq/L",
                    mion.type, mion.parcj(), 1000 * mion.parmj(), mion.parzj()));
        }
        Assert.assertEquals(tds, stream.tds(), 1e-10);
    }

    @Test(timeout = 10000, expected = TimeoutException.class)
    public void SystemTest() throws Exception {
        MLogger.logen(true, false, false);
        MSystem system = new MSystem();
        system.pariQp = 10;
        system.pariY = 75;
        system.pariQr = 0;
        system.streams.ion(EIon.Ca).parzj(0.01);
        system.streams.ion(EIon.HCO3).parzj(0.01);
        system.calculate();
    }

    @Test(timeout = 10000, expected = ArithmeticException.class)
    public void MemNaNTest() throws Exception {
        MLogger.logen(true, true, true);
        MSystem system = new MSystem();
        system.pariQp = 17;
        system.pariY = 50;
        system.pariQr = 1;
        system.streams.ion(EIon.Ca).parzj(0.01);
        system.streams.ion(EIon.HCO3).parzj(0.01);
        system.calculate();
    }

    @Test(timeout = 10000, expected = TimeoutException.class)
    public void MemLooTest() throws Exception {
        MLogger.logen(true, true, true);
        MSystem system = new MSystem();
        system.pariQp = 17;
        system.pariY = 75;
        system.pariQr = 0;
        system.streams.ion(EIon.Ca).parzj(0.01);
        system.streams.ion(EIon.HCO3).parzj(0.01);
        system.calculate();
    }

    @Test
    public void MemCodTest() throws Exception {
        MLogger.logen(false, false, false);
        MSystem system = new MSystem();
        system.pariQp = 10;
        system.pariY = 50;
        system.pariQr = 0;
        system.streams.ion(EIon.Ca).parzj(0.01);
        system.streams.ion(EIon.HCO3).parzj(0.01);
        system.streams.codmode(true);
        system.streams.cods()[0].parcj = 400;
        system.calculate();
        for (MStream stream : new MStream[]{system.streams, system.streamf, system.streamc, system.streamp}) {
            for (MCOD cod : stream.cods()) {
                Assert.assertTrue(cod.parcj < 1000);
            }
        }
    }
}
