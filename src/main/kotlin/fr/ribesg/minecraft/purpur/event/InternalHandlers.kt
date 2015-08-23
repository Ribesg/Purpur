package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.event.EventHandler as eventHandler
import fr.ribesg.minecraft.purpur.event.EventHandlerPriority as Priority

/**
 * @author Ribesg
 */
public object InternalHandlers {

    eventHandler(Priority.INTERNAL)
    public fun onRawLine(e: RawLineEvent) {
        EventManager.call(LineEvent(e.line.trim()))
    }

    eventHandler(Priority.INTERNAL)
    public fun onLine(e: LineEvent) {
        when (e.content) {
            "Generating new properties file"
            -> EventManager.call(CreateServerPropertiesEvent())
            "You need to agree to the EULA in order to run the server. Go to eula.txt for more info."
            -> EventManager.call(EulaNeedAgreeEvent())
        }
    }
}
