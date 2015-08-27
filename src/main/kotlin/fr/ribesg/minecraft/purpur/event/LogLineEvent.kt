package fr.ribesg.minecraft.purpur.event

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class LogLineEvent(line: String) : Event() {

    private companion object {
        val LOG_REGEX = "\\[(..:..:..)\\] \\[(.+)/(.+)\\]: (.+)".toRegex()

        fun parse(line: String): List<String> {
            return LOG_REGEX
                .match(line)!!
                .groups
                .drop(1)
                .map { it!!.value }
                .toCollection(ArrayList<String>())
        }
    }

    public val time: Calendar
    public val thread: String
    public val logLevel: String
    public val content: String

    init {
        val (timeString, t, l, c) = parse(line)
        time = Calendar.getInstance()
        time.setTime(SimpleDateFormat("HH:mm:ss").parse(timeString))
        thread = t
        logLevel = l
        content = c
    }
}
