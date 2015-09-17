package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Ribesg
 */
data class LogLineEvent(line: String) : Event() {

    val time: Calendar
    val thread: String
    val logLevel: String
    val content: String

    init {
        val (timeString, t, l, c) = Props.regexLine.parse(line)
        time = Calendar.getInstance()
        time.time = SimpleDateFormat("HH:mm:ss").parse(timeString)
        thread = t
        logLevel = l
        content = c
    }
}
