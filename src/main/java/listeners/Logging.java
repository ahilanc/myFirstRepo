package listeners;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;


public class Logging {
    private static Logger Log = LogManager.getLogger(Logging.class.getName());
    private String logLevel;

    public static void info(String message) {
        Log.info(message);
    }

    /**
     *
     * @param clazz
     * @param message
     */
    public static void debug(Class<? extends Object> clazz, String message) {
        Log.debug(message);
    }

    public static int logError(final String errorMsg) {
        Log.error(errorMsg);
        return logError(errorMsg);
    }

    public static void logWarn(final String message) {
        Log.warn(String.format("********************************************************************************"));
        Log.warn(String.format("* WARNING:"));
        Log.warn(String.format("*"));
        prettyPrintWarningMessage(message);
        Log.warn(String.format("********************************************************************************"));

    }

    /**
     * pretty print the warning message supplied
     *
     * @param message the message
     */
    private static void prettyPrintWarningMessage(String message) {
        StringBuilder builder = new StringBuilder(message);
        while (builder.length() > 70) {
            int space = builder.lastIndexOf(" ", 70);
            if (space <= 0) space = 70;
            Log.warn(String.format("* %s", builder.substring(0, space)));
            builder.delete(0, space + 1);
        }
        Log.warn(String.format("* %s", builder));
    }

    //logging starts
    public void startLog(String testClassName){
        logLevel = "info";
        Configurator.setRootLevel(setlogLevel());
        //Log.setLevel(setlogLevel());
        Log.info(testClassName);
    }

    //logging ends
    public void endLog(String testClassName) {
        Log.info(testClassName);
        LogManager.shutdown();
    }

    // returns any of the Log4j supported log level
    private Level setlogLevel() {
        Level logLevel = null;
        switch (this.logLevel.toLowerCase()) {
            case "info":
                logLevel = Level.INFO;
                break;
            case "debug":
                logLevel = Level.DEBUG;
                break;
            case "warn":
                logLevel = Level.WARN;
                break;
            case "error":
                logLevel = Level.ERROR;
                break;
        }
        return logLevel;
    }



}
