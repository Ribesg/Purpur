package fr.ribesg.minecraft.purpur

import java.util.ArrayList
import kotlin.text.Regex

/**
 * @author Ribesg
 */

/**
 * Parses a String using a [Regex].
 *
 * The String should match the regex and have at least one group.
 */
public fun Regex.parse(s: String): List<String>
    = this.match(s)!!.groups.drop(1).map { it!!.value }.toCollection(ArrayList<String>())
