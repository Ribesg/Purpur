package fr.ribesg.minecraft.purpur.api.event

import java.lang.reflect.Method

/**
 * @author Ribesg
 */
class InvalidEventHandlerException(
    method: Method, reason: String
) : Exception(
    "Method " + method.declaringClass.name + '.' +
    method.name + "(...) isn't a valid EventHandler: $reason"
)
