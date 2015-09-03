package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class LogLineEvent(line: String) : Event() {

    public val time: Calendar
    public val thread: String
    public val logLevel: String
    public val content: String

    init {
        val (timeString, t, l, c) = Props.regexLine.parse(line)
        time = Calendar.getInstance()
        time.setTime(SimpleDateFormat("HH:mm:ss").parse(timeString))
        thread = t
        logLevel = l
        content = c
    }
}
