package church.thegrowpoint.foundations.modules

import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * # BaseViewModel
 *
 * The base view model class for foundations view models.
 *
 * @property appContext The application context.
 */
abstract class BaseViewModel (@ApplicationContext context: Context): ViewModel() {
    /**
     * The application context.
     */
    protected var appContext: Context = context

    /**
     * Show a toast message.
     *
     * @param message the toast message to show.
     * @param duration the duration of the toast message.
     */
    protected fun showToastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        val thread = Thread {
            // Initialize the message queue for the thread
            Looper.prepare()

            Toast.makeText(appContext, message, duration).show()

            Looper.loop()
        }

        thread.start()
    }
}
