package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class LogLineEvent(line: String) : Event() {

    private companion object {
        fun parse(line: String): List<String> {
            return Props.regexLine
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
