package bizverse.lab.healthylifestyle.utils.extensions

import android.content.Context
import android.content.res.Resources
import bizverse.lab.healthylifestyle.R
import bizverse.lab.healthylifestyle.data.enums.EExercise
import bizverse.lab.healthylifestyle.data.enums.sleep.ESleep
import bizverse.lab.healthylifestyle.utils.Constants
import bizverse.lab.healthylifestyle.utils.Constants.Companion.EXCELLENT_QUALITY
import bizverse.lab.healthylifestyle.utils.Constants.Companion.GENERAL_QUALITY
import bizverse.lab.healthylifestyle.utils.Constants.Companion.GOOD_QUALITY
import bizverse.lab.healthylifestyle.utils.Constants.Companion.VALUE_FEET
import bizverse.lab.healthylifestyle.utils.Constants.Companion.VALUE_INCHES
import bizverse.lab.healthylifestyle.utils.Constants.Companion.VALUE_MILE
import bizverse.lab.healthylifestyle.utils.Constants.Companion.VALUE_POUND
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import kotlin.math.floor
import kotlin.math.round

/*
* Created by ducnguyen on 04/06/2022.
*/

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.getSleepType(): ESleep {
    return when (this) {
        in Constants.DEEP_SLEEP -> {
            ESleep.DEEP_SLEEP
        }
        in Constants.LIGHT_SLEEP -> {
            ESleep.LIGHT_SLEEP
        }
        else -> ESleep.REM_SLEEP
    }
}

fun Long.toHourHMinuteMin(): LongArray {
    val hour = TimeUnit.MILLISECONDS.toHours(this)
    val minute = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hour)
    return LongArray(2).apply {
        set(0, hour)
        set(1, minute)
    }
}

fun Long.fromMinuteToHHMM(pattern: String = Constants.H2D_M2D): String {
    val hour = TimeUnit.MINUTES.toHours(this)
    val minute = TimeUnit.MINUTES.toMinutes(this) - TimeUnit.HOURS.toMinutes(hour)
    return String.format(pattern, hour, minute)
}

fun Long.unit(unit: TimeUnit, beginUnit: TimeUnit = TimeUnit.MILLISECONDS) = when (unit) {
    TimeUnit.HOURS -> beginUnit.toHours(this)
    TimeUnit.DAYS -> beginUnit.toDays(this)
    TimeUnit.MINUTES -> beginUnit.toMinutes(this)
    TimeUnit.MICROSECONDS -> beginUnit.toMicros(this)
    TimeUnit.NANOSECONDS -> beginUnit.toNanos(this)
    TimeUnit.SECONDS -> beginUnit.toSeconds(this)
    TimeUnit.MILLISECONDS -> beginUnit.toMillis(this)
    else -> beginUnit.toMillis(this)
}

fun Int.minToHourFloat(): Float = this / 60f

fun Int.secondFormatToHourMinute(
    firstFormat: String = "%02d:",
    secondFormat: String = "%02d"
): String {
    return run {
        val hours = this / 3600
        val minutes = (this - hours * 3600) / 60
        "${String.format(firstFormat, hours)}${String.format(secondFormat, minutes)}"
    }
}

fun Int.minuteFormatToHour(
    firstFormat: String = "%02d:",
    secondFormat: String = "%02d"
): String {
    return run {
        val hours = (this / 60f).toInt()
        val minute = this - hours * 60
        "${String.format(firstFormat, hours)}${String.format(secondFormat, minute)}"
    }
}

fun Long.secondFormatToHourMinute(
    firstFormat: String = "%02d:",
    secondFormat: String = "%02d",
    isShowSecond: Boolean = false
): String {
    return run {
        val hours = this / 3600
        var temp = this - hours * 3600
        val mins = temp / 60
        temp -= mins * 60
        val secs = temp
        if (isShowSecond)
            "${String.format(firstFormat, hours)}${
                String.format(
                    secondFormat,
                    mins
                )
            }${String.format("%02d", secs)}"
        else
            "${String.format(firstFormat, hours)}${String.format(secondFormat, mins)}"
    }
}

fun Long.minuteFormatToHour(
    firstFormat: String = "%02d:",
    secondFormat: String = "%02d"
): String {
    return run {
        val hours = this / 60
        val minute = this - hours * 60
        "${String.format(firstFormat, hours)}${String.format(secondFormat, minute)}"
    }
}

fun Long.minToMinutesString(): String = (this / 60).toString()

fun Int?.minToMinutesString(): String = if (this != null) (this / 60).toString() else "0"

fun Float.hourToMin(): Int = (this * 60).toInt()

fun Double?.toFormatNumberString(formatNumber: String = "###,###,###,###.##"): String {
    val format = DecimalFormat(formatNumber)
    return if (this != null && !this.isInfinite() && !this.isNaN())
        format.format(this)
    else
        "0"
}

fun Float?.toFormatNumberString(formatNumber: String = "###,###,###,###.##"): String {
    val format = DecimalFormat(formatNumber)
    return if (this != null)
        format.format(this)
    else
        "0"
}

fun Int.toFormatNumberString(formatNumber: String = "###,###,###,###"): String {
    val format = DecimalFormat(formatNumber)
    return format.format(this)
}

fun Int.convertKgToLb() = round(this * VALUE_POUND).toInt()

fun Int.convertLbToKg() = round(this / VALUE_POUND).toInt()

fun Int.convertFtToCm() = this * VALUE_FEET

fun Int.convertInchesToCm() = this * VALUE_INCHES

fun Int.convertCmToFtAndIn(): Pair<Int, Int> {
    val feet = floor(this / VALUE_FEET)
    val inches = round(this / VALUE_INCHES) - (feet * 12)
    return Pair(feet.toInt(), inches.toInt())
}

fun Int.toGender(context: Context) =
    context.getString(if (this == 1) R.string.male else R.string.female)

fun Float.toMinPerKm(): Float = this * 1000 / 60

fun Float.toTimeStringFormat(pattern: String = Constants.m_s): String {
    val min = this.toInt()
    val second = ((this - min) * 60).toInt()
    return String.format(pattern, min, second)
}

fun Int.toExerciseModeString(): Int {
    return when (this) {
        EExercise.RUN.mode -> R.string.running
        EExercise.CYCLING.mode -> R.string.cycling
        EExercise.BADMINTON.mode -> R.string.badminton
        EExercise.FOOTBALL.mode -> R.string.football
        EExercise.TENNIS.mode -> R.string.tennis
        EExercise.YOGA.mode -> R.string.yoga
        EExercise.BREATH.mode -> R.string.meditation
        EExercise.DANCE.mode -> R.string.dancing
        EExercise.BASKETBALL.mode -> R.string.basketball
        EExercise.HIKING.mode -> R.string.hiking
        EExercise.WORKOUT.mode -> R.string.workout
        else -> R.string.running
    }
}

fun Int.toHrStatusString(): Int? {
    return when {
        this in Constants.Firebase.ZONE_WARM_UP -> R.string.warm_up
        this in Constants.Firebase.ZONE_FAT_BURN -> R.string.fat_burn
        this in Constants.Firebase.ZONE_AEROBIC -> R.string.aerobic
        this in Constants.Firebase.ZONE_ANAEROBIC -> R.string.anaerobic
        this >= Constants.Firebase.ZONE_HIGH -> R.string.hr_high
        else -> null
    }
}

fun Int.toHRStatus(age: Int): Int? {
    val mhr = Constants.Firebase.DEFAULT_MHR - age
    val realBPM = (this.toFloat() / mhr.toFloat() * 100).toInt()
    return realBPM.toHrStatusString()
}

fun Int?.toBoolean() = this == 1

fun Double.covertKmToMile() = this * VALUE_MILE

fun Int.toSleepQuality(): Int =
    when (this) {
        in EXCELLENT_QUALITY -> {
            R.string.excellent
        }
        in GOOD_QUALITY -> {
            R.string.good
        }
        in GENERAL_QUALITY -> {
            R.string.general
        }
        else -> {
            R.string.poor
        }
    }

fun Int.toSleepQualityDescription(): Int =
    when (this) {
        in EXCELLENT_QUALITY -> {
            R.string.excellent_desc
        }
        in GOOD_QUALITY -> {
            R.string.good_desc
        }
        in GENERAL_QUALITY -> {
            R.string.general_desc
        }
        else -> {
            R.string.poor_desc
        }
    }

fun Int.toSleepQualityColor(): Int =
    when (this) {
        in EXCELLENT_QUALITY -> {
            R.color.excellent_sleep
        }
        in GOOD_QUALITY -> {
            R.color.good_sleep
        }
        in GENERAL_QUALITY -> {
            R.color.general_sleep
        }
        else -> {
            R.color.poor_sleep
        }
    }

fun Int.toSleepQualityProgress(): Float =
    when (this) {
        in EXCELLENT_QUALITY -> 99f
        in GOOD_QUALITY -> 74f
        in GENERAL_QUALITY -> 49f
        else -> 24f
    }
