package bizverse.lab.healthylifestyle.utils.extensions

import androidx.core.text.HtmlCompat
import bizverse.lab.healthylifestyle.utils.Constants.Companion.YYYY_MM_DD_HH_MM_SS
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.uuid(): UUID = UUID.fromString(this)

fun String.toCalendar(pattern: String = YYYY_MM_DD_HH_MM_SS): Calendar? {
    val format = SimpleDateFormat(pattern, Locale.ENGLISH)
    try {
        val date = format.parse(this@toCalendar) ?: return null
        val result = Calendar.getInstance().apply {
            time = date
        }
        return result
    } catch (e: ParseException) {
        return null
    }
}

fun String?.fromHHmmToMinutes(): Int {
    if (this.isNullOrBlank()) return 0
    val split = this.split(":")
    val hour = split.firstOrNull()?.toLongOrNull() ?: 0
    val minute = split.lastOrNull()?.toIntOrNull() ?: 0
    return (TimeUnit.HOURS.toMinutes(hour) + minute).toInt()
}

fun String.fromHtml() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
