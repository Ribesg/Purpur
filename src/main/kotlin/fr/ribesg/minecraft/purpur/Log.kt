package fr.ribesg.minecraft.purpur

import java.util.logging.ConsoleHandler
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Ribesg
 */
public object Log {

    private val logger: Logger

    init {
        logger = Logger.getLogger("Purpur")
        System.setProperty(
            "java.util.logging.SimpleFormatter.format",
            "%n%5\$s %6\$s"
        )
        for (h in logger.getHandlers ()) {
            logger.removeHandler(h);
        }
        logger.addHandler(object : ConsoleHandler() {
            init {
                this.setOutputStream(System.out);
                this.setLevel(Level.ALL);
            }
        });

        Runtime.getRuntime().addShutdownHook(Thread { Log.info("") })
    }

    public fun isDebugEnabled(): Boolean = logger.isLoggable(Level.FINEST)


    public fun setDebugEnabled(value: Boolean) {
        logger.setLevel(if (value) Level.FINEST else Level.INFO)
    }

    public fun isServerLogEnabled(): Boolean = logger.isLoggable(Level.FINE)

    public fun setServerLogEnabled(value: Boolean) {
        logger.setLevel(if (value) Level.FINE else Level.INFO)
    }

    public fun debug(message: Any) {
        logger.log(Level.FINEST, "[PURPUR] " + message.toString())
    }

    public fun debug(message: Any, t: Throwable) {
        logger.log(Level.FINEST, "[PURPUR] " + message.toString(), t)
    }

    public fun info(message: Any? = null) {
        if (message == null || "".equals(message.toString())) {
            logger.log(Level.INFO, "")
        } else {
            logger.log(Level.INFO, "[PURPUR] " + message.toString())
        }
    }

    public fun server(serverLogLine: String) {
        logger.log(Level.FINE, "[SERVER] " + serverLogLine)
    }

    public fun error(message: Any) {
        logger.log(Level.SEVERE, "[PURPUR] " + message.toString())
        flush()
    }

    public fun error(message: Any, t: Throwable) {
        logger.log(Level.SEVERE, "[PURPUR] " + message.toString(), t)
        flush()
    }

    public fun error(t: Throwable) {
        logger.log(Level.SEVERE, "[PURPUR] Error caught", t)
        flush()
    }

    public fun flush() {
        logger.getHandlers().forEach(Handler::flush)
    }
}
