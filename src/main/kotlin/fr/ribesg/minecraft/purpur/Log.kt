package fr.ribesg.minecraft.purpur

import java.util.logging.ConsoleHandler
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Ribesg
 */
object Log {

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

    fun isDebugEnabled(): Boolean = logger.isLoggable(Level.FINEST)


    fun setDebugEnabled(value: Boolean) {
        logger.setLevel(if (value) Level.FINEST else Level.INFO)
    }

    fun isServerLogEnabled(): Boolean = logger.isLoggable(Level.FINE)

    fun setServerLogEnabled(value: Boolean) {
        logger.setLevel(if (value) Level.FINE else Level.INFO)
    }

    fun debug(message: Any) {
        logger.log(Level.FINEST, "[PURPUR] " + message.toString())
    }

    fun debug(message: Any, t: Throwable) {
        logger.log(Level.FINEST, "[PURPUR] " + message.toString(), t)
    }

    fun info(message: Any? = null) {
        if (message == null || "".equals(message.toString())) {
            logger.log(Level.INFO, "")
        } else {
            logger.log(Level.INFO, "[PURPUR] " + message.toString())
        }
    }

    fun server(serverLogLine: String) {
        logger.log(Level.FINE, "[SERVER] " + serverLogLine)
    }

    fun error(message: Any) {
        logger.log(Level.SEVERE, "[PURPUR] " + message.toString())
        flush()
    }

    fun error(message: Any, t: Throwable) {
        logger.log(Level.SEVERE, "[PURPUR] " + message.toString(), t)
        flush()
    }

    fun error(t: Throwable) {
        logger.log(Level.SEVERE, "[PURPUR] Error caught", t)
        flush()
    }

    fun flush() {
        logger.getHandlers().forEach(Handler::flush)
    }
}
