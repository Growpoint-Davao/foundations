package church.thegrowpoint.foundations.utils.extensions

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Converts a json string to a hashmap.
 */
fun String.toStringHashMap(): HashMap<String, String> {
    return Json.decodeFromString<HashMap<String, String>>(this)
}

/**
 * Converts a hashmap to a json string.
 */
fun HashMap<String, String>.toJsonString(): String {
    return Json.encodeToString(this)
}
