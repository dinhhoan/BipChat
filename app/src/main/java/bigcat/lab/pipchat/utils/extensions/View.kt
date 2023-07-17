package bizverse.lab.healthylifestyle.utils.extensions

import android.os.SystemClock
import android.view.View
import bizverse.lab.healthylifestyle.utils.Constants

fun View.disableMultipleClick(
    timeDelay: Int = Constants.TIME_DELAY_DEFAULT,
    eventClick: (view: View) -> Unit
) {
    var timeNow = 0L
    setOnClickListener {
        SystemClock.elapsedRealtime().run {
            if (this - timeNow < timeDelay) {
                return@setOnClickListener
            }
            eventClick.invoke(it)
            timeNow = this
        }
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.showIf(predicate: Boolean, actionWhenVisible: () -> Unit = {}) {
    if (predicate) {
        visible()
        actionWhenVisible()
    } else {
        gone()
    }
}
