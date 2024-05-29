package church.thegrowpoint.foundations.utils.extensions

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

/**
 * The extension for retrieving the current activity from a given context.
 *
 * @return The current activity instance.
 */
fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
