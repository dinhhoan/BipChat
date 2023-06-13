package bizverse.lab.healthylifestyle.utils.extensions

import android.content.Context
import android.graphics.Typeface
import android.os.SystemClock
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import bizverse.lab.healthylifestyle.R
import java.util.regex.Pattern

/**
 * @author mvn-toan.nguyen2 on 6/7/22
 **/

fun TextView.setTextWithHighLight(
    originString: String,
    highLightColor: Int,
    vararg textHighLights: String,
    isUnderLine: Boolean = false,
    highLightStyle: Int = Typeface.NORMAL,
    clickHighLight: (str: String) -> Unit = {}
) {
    movementMethod = LinkMovementMethod()
    text = SpannableString(originString).apply {
        textHighLights.forEach { textHighLight ->
            Pattern.compile(textHighLight).matcher(originString).let { matcher ->
                val textFind = matcher.find()
                if (textFind) {
                    val clickSpan = object : ClickableSpan() {
                        var timeNow = 0L
                        override fun onClick(p0: View) {
                            SystemClock.elapsedRealtime().run {
                                if (this - timeNow < 1500L) {
                                    return
                                }
                                clickHighLight(textHighLight)
                                timeNow = this
                            }
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.isUnderlineText = isUnderLine
                        }
                    }
                    this.setSpan(
                        clickSpan,
                        matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    this.setSpan(
                        ForegroundColorSpan(highLightColor),
                        matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    this.setSpan(
                        StyleSpan(highLightStyle), matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
    }
}

fun TextView.handleTextCompare(
    totalDataNow: Int,
    totalDataLast: Int,
    ctx: Context,
    isShowIcon: Boolean = false
) {
    when {
        totalDataNow > totalDataLast -> {
            this.setTextColor(ContextCompat.getColor(ctx, R.color.primary))
            if (isShowIcon)
                setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(
                        ctx,
                        R.drawable.ic_arrow_up_high_light
                    ), null, null, null
                )
        }
        totalDataNow < totalDataLast -> {
            this.setTextColor(ContextCompat.getColor(ctx, R.color.on_background))
            if (isShowIcon)
                setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(
                        ctx,
                        R.drawable.ic_arrow_down
                    ), null, null, null
                )
        }
        else -> {
            this.setTextColor(ContextCompat.getColor(ctx, R.color.on_background))
            if (isShowIcon)
                setCompoundDrawables(
                    null, null, null, null
                )
        }
    }
}
