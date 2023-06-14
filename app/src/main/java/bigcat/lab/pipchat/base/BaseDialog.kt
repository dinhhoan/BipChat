package bigcat.lab.pipchat.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

/**
 * @author hoantd on 6/22/22
 **/
abstract class BaseDialog : DialogFragment() {
    companion object {
        private const val dimValue = 0.5f
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext()) {

        }.apply {
            window?.run {
                requestFeature(Window.FEATURE_NO_TITLE)
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                )
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setDimAmount(dimValue)
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setContentDialog(this)
            initListeners(this)
        }
    }

    abstract fun setContentDialog(dialog: Dialog)

    abstract fun initListeners(dialog: Dialog)
}