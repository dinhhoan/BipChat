package bizverse.lab.healthylifestyle.utils

import android.util.Log
import androidx.viewbinding.BuildConfig

/*
* Created by ducnguyen on 27/06/2022.
*/
class LogManager {
    companion object {
        private const val TAG = "Bizverse"
        fun logD(message: String, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, message)
            }
        }

        fun logWtf(message: String, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                Log.wtf(tag, message)
            }
        }
    }
}
