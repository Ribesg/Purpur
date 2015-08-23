package fr.ribesg.minecraft.purpur.event

/**
 * @author Ribesg
 */

public data class RawLineEvent(val line: String) : Event()

public class ServerReadyEvent : Event()

public class ServerStoppedEvent : Event()

public class CreateServerPropertiesEvent : Event()

public class EulaNeedAgreeEvent : Event()
