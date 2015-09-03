package fr.ribesg.minecraft.purpur.api.event

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Annotation used to mark Event handlers.
 *
 * This annotation should only be used on public methods having a single
 * parameter of type [Event] or a subclass of Event.
 *
 * @author Ribesg
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public annotation class EventHandler(
    val priority: EventHandlerPriority = EventHandlerPriority.DEFAULT,
    val ignoreConsumed: Boolean = true
)
