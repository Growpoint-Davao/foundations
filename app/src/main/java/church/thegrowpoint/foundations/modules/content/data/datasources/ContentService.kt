package church.thegrowpoint.foundations.modules.content.data.datasources

import church.thegrowpoint.foundations.modules.content.data.models.Content
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

/**
 * # Content Service
 *
 * The interface for reading content.
 */
interface ContentService {
    val content: Content?

    companion object {
        const val CONTENT_FILENAME = "content.json"
    }
}

/**
 * The extension function to convert [JSONObject] to [Map]
 */
@Throws(JSONException::class)
fun JSONObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    val keysItr: Iterator<String> = this.keys()
    while (keysItr.hasNext()) {
        val key = keysItr.next()
        var value: Any = this.get(key)
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        map[key] = value
    }
    return map
}

/**
 * The extension function to convert [JSONArray] to [List]
 */
@Throws(JSONException::class)
fun JSONArray.toList(): List<Any> {
    val list = mutableListOf<Any>()
    for (i in 0 until this.length()) {
        var value: Any = this[i]
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        list.add(value)
    }
    return list
}

/**
 * # ContentServiceImplementation
 *
 * The implementation of [ContentService].
 *
 * @property assetService The asset service instance.
 */
class ContentServiceImplementation @Inject constructor(
    private val assetService: AssetsService
) : ContentService {
    /**
     * The content model instance.
     */
    private var _content: Content? = null

    /**
     * Retrieves the content model instance.
     */
    override val content: Content?
        get() = _content

    init {
        _content = parseJsonContent()
    }

    /**
     * Resolves any [content] to a [String].
     */
    private fun resolveContent(content: Any): String {
        if (content is String) {
            return content
        } else if (content is List<*>) {
            return content.joinToString(separator = "\n")
        }

        return content.toString()
    }

    /**
     * Converts any [items] which are not [String] to a [List] of [String].
     */
    private fun convertToStrings(items: List<*>): List<String> {
        return items.mapNotNull {
            it?.let { resolveContent(it) }
        }
    }

    /**
     * Parses the content from the asset.
     */
    private fun parseJsonContent(): Content? {
        val jsonContent = assetService.readAsset(ContentService.CONTENT_FILENAME)

        try {
            // parse and convert to object
            val map = jsonContent?.let { JSONObject(it).toMap() }

            if (map != null) {
                val introduction = (map["introduction"] as List<*>).map {
                    convertToStrings(it as List<*>)
                }

                val salvation = (map["salvation"] as List<*>).map {
                    convertToStrings(it as List<*>)
                }

                return Content(
                    gettingStarted = introduction,
                    salvation = salvation
                )
            }

            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
