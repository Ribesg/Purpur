package fr.ribesg.minecraft.purpur.event

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * @author Ribesg
 */
public Target(ElementType.METHOD) Retention(RetentionPolicy.RUNTIME) annotation class EventHandler(
    val priority: EventHandlerPriority = EventHandlerPriority.DEFAULT,
    val ignoreConsumed: Boolean = true
)
