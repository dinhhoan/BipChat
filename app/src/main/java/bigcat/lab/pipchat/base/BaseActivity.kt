package bigcat.lab.pipchat.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import bigcat.lab.pipchat.utils.PermissionsUtil
import bizverse.lab.healthylifestyle.utils.extensions.hideKeyboard
import kotlinx.coroutines.*

/*
* Created by hoantd on 31/05/2022.
*/
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    companion object {
        //40s
        private const val TIME_DELAY_CHECK_INTERNET = 40000L
    }

    abstract val bindingInflate: (LayoutInflater) -> V
    private var binding: V? = null
    protected fun <V : ViewBinding> fromVB(inflater: (LayoutInflater) -> V) = inflater
    private var jobCheckInternet: Job? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        hideSystemUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = bindingInflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    internal fun checkPermission(
        permissionKey: Int,
        onRequestPermission: (Array<out String>) -> Unit,
        onPermissionBlocked: () -> Unit
    ) {
        val permission = PermissionsUtil.getArrayNameByPermissionKey(permissionKey)
        if (PermissionsUtil.shouldShowRequestPermissionRationale(this, permissionKey)) {
            onRequestPermission(permission)
        } else {
            onPermissionBlocked.invoke()
        }
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightNavigationBars = false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                hideKeyboardWhenTapOutsideEditText(ev).run {
                    return if (this) {
                        this
                    } else {
                        super.dispatchTouchEvent(ev)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboardWhenTapOutsideEditText(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view is EditText) {
            val rect = Rect()
            view.getGlobalVisibleRect(rect)

            val bound = IntArray(2)
            view.getLocationOnScreen(bound)
            val x = ev.rawX + view.getLeft() - bound[0]
            val y = ev.rawY + view.getTop() - bound[1]

            if (!rect.contains(ev.x.toInt(), ev.y.toInt())
                || x < view.getLeft() || x > view.getRight()
                || y < view.getTop() || y > view.getBottom()
            ) {
                hideKeyboard(this)
                view.clearFocus()
                return true
            }
        }
        return false
    }

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    )
                        return true
                }
            } else {
                try {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        Log.i("update_statut", "Network is available : true")
                        return true
                    }
                } catch (e: Exception) {
                    Log.i("update_statut", "" + e.message)
                }
            }
        }
        Log.i("update_statut", "Network is available : FALSE ")
        return false
    }

    internal fun startCheckInternet(doTaskUnavailableInternet: () -> Unit = {}) {
        jobCheckInternet = CoroutineScope(Dispatchers.Main).launch {
            delay(TIME_DELAY_CHECK_INTERNET)
            if (!isNetworkAvailable(this@BaseActivity)) {
                doTaskUnavailableInternet()
            } else {
                return@launch
            }
        }
    }

    internal fun cancelCheckInternet() {
        jobCheckInternet?.cancel()
    }
}
