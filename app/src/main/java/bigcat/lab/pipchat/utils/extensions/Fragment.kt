package bizverse.lab.healthylifestyle.utils.extensions

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import bigcat.lab.pipchat.base.BaseActivity
import bigcat.lab.pipchat.utils.PermissionsUtil
import bitcat.lab.pipchat.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip

/**
 * @author mvn-toan.nguyen2 on 7/4/22
 **/

internal fun Fragment.checkPermissionRequest(
    permissionKey: Int,
    onRequestSuccess: () -> Unit,
    onPermissionBlocked: () -> Unit
) {
    if (!PermissionsUtil.checkSelfPermission(requireContext(), permissionKey)) {
        onRequestSuccess.invoke()
    } else {
        (activity as BaseActivity<*>).checkPermission(permissionKey, {
            requestPermissions(it, permissionKey)
        }) {
            onPermissionBlocked.invoke()
        }
    }
}

internal fun Fragment.showAlertDialogTwoButton(
    title: String? = "",
    message: String? = "",
    titlePositiveButton: String? = getString(R.string.dialog_alert_text_ok),
    titleNegativeButton: String? = getString(R.string.activity),
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {}
) {
    val dialog =
        AlertDialog.Builder(context, R.style.Theme_BaseAppKotlin).apply {
            setCancelable(false)
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                titlePositiveButton
            ) { dialog, _ ->
                onPositiveButtonClick()
                dialog.dismiss()
            }
            setNegativeButton(
                titleNegativeButton
            ) { dialog, _ ->
                onNegativeButtonClick()
                dialog.dismiss()
            }
        }
    dialog.show()
}

internal fun Fragment.showAlertDialogOneButton(
    title: String? = "",
    message: String? = "",
    titlePositiveButton: String? = getString(R.string.dialog_alert_text_ok),
    onPositiveButtonClick: () -> Unit = {}
) {
    val dialog =
        AlertDialog.Builder(context, R.style.Theme_BaseAppKotlin).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                titlePositiveButton
            ) { dialog, _ ->
                onPositiveButtonClick()
                dialog.dismiss()
            }
            setCancelable(false)
        }
    dialog.show()
}

internal fun <T : Any> Fragment.setBackStackData(key: String, data: T, doBack: Boolean = true) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
    if (doBack)
        findNavController().navigateUp()
}

internal fun <T : Any> Fragment.getBackStackData(key: String, result: (T) -> (Unit)) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
        ?.observe(viewLifecycleOwner) {
            result(it)
        }
}

fun <T> Fragment.collectFlow(targetFlow: Flow<T>, collectBlock: ((T) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            targetFlow.collect {
                collectBlock.invoke(it)
            }
        }
    }
}

private inline fun Fragment.safeViewCollect(crossinline viewOwner: LifecycleOwner.() -> Unit) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            viewLifecycleOwnerLiveData.observe(
                this@safeViewCollect
            ) { viewLifecycleOwner ->
                viewLifecycleOwner.viewOwner()
            }
        }
    })
}


fun <T1, T2> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    collectBlock: ((T1, T2) -> Unit)
) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            flow1.combine(flow2) { v1, v2 ->
                collectBlock.invoke(v1, v2)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2, T3> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    collectBlock: ((T1, T2, T3) -> Unit)
) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            combine(flow1, flow2, flow3) { v1, v2, v3 ->
                collectBlock.invoke(v1, v2, v3)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2, T3, T4> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    collectBlock: ((T1, T2, T3, T4) -> Unit)
) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
                collectBlock.invoke(v1, v2, v3, v4)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2> Fragment.zipFlows(flow1: Flow<T1>, flow2: Flow<T2>, collectBlock: ((T1, T2) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            flow1.zip(flow2) { v1, v2 ->
                collectBlock.invoke(v1, v2)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

internal fun Fragment.getDeviceSettingIntent(): Intent {
    return Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:${activity?.packageName}")
    ).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}
