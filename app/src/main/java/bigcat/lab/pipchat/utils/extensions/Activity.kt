package bizverse.lab.healthylifestyle.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import bitcat.lab.pipchat.R

/**
 * @author mvn-toan.nguyen2 on 6/23/22
 **/

inline fun <reified T : Any> Activity.startActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent,9789, options)
}

inline fun <reified T : Any> Activity.startActivityWithPushData(noinline init: Intent.() -> Unit = {}) {
    startActivity<T> {
        data = intent?.data
        intent?.extras?.also {
            putExtras(it)
        }
        init()
    }
    intent = null
}


inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

internal fun Activity.showAlertDialogOneButton(
    title: String? = "",
    message: String? = "",
    titlePositiveButton: String? = getString(R.string.dialog_alert_text_ok),
    onPositiveButtonClick: () -> Unit = {}
) {
//    val dialog =
//        AlertDialog.Builder(this, R.style.DialogThemeLight).apply {
//            setTitle(title)
//            setMessage(message)
//            setPositiveButton(
//                titlePositiveButton
//            ) { dialog, _ ->
//                onPositiveButtonClick()
//                dialog.dismiss()
//            }
//            setCancelable(false)
//        }
//    dialog.show()
}
