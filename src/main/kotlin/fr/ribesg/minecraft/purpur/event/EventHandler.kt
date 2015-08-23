package fr.ribesg.minecraft.purpur.event

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention as retention
import java.lang.annotation.Target as target

/**
 * Annotation used to mark Event handlers.
 *
 * This annotation should only be used on public methods having a single
 * parameter of type [Event] or a subclass of Event.
 *
 * @author Ribesg
 */
target(ElementType.METHOD)
retention(RetentionPolicy.RUNTIME)
public annotation class EventHandler(
    val priority: EventHandlerPriority = EventHandlerPriority.DEFAULT,
    val ignoreConsumed: Boolean = true
)
