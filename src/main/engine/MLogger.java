package engine;

import jdk.nashorn.internal.runtime.logging.DebugLogger;

import java.util.logging.Level;

public class MLogger {
    private static DebugLogger logger = new DebugLogger("DF", Level.ALL, false);
    private static boolean ensys = false, ensec = false, enmem = false;

    private static void log(String sec, String msg) {
        logger.log(Level.INFO, String.format("{%s}%s", sec, msg));
    }

    public static void logen(boolean sys, boolean sec, boolean mem) {
        ensys = sys;
        ensec = sec;
        enmem = mem;
    }

    public static void syslog(String msg) {
        if (ensys) {
            log("SYS", msg);
        }
    }

    public static void seclog(String msg) {
        if (ensec) {
            log("SEC", msg);
        }
    }

    public static void memlog(String msg) {
        if (enmem) {
            log("MEM", msg);
        }
    }
}
