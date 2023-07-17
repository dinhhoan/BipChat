package bigcat.lab.pipchat.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity

object PermissionsUtil {

    internal const val REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 1235
    internal const val REQUEST_CAMERA_PERMISSION_CODE = 6996

    private val GALLERY = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val CAMERA = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    internal fun checkSelfPermission(context: Context, permissionKey: Int) =
        getArrayNameByPermissionKey(permissionKey).any {
            context.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
        }

    internal fun shouldShowRequestPermissionRationale(
        activity: AppCompatActivity,
        permissionKey: Int
    ) =
        getArrayNameByPermissionKey(permissionKey).any {
            activity.shouldShowRequestPermissionRationale(it)
        }

    internal fun getArrayNameByPermissionKey(permissionKey: Int) =
        when (permissionKey) {
            REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_CODE -> GALLERY
            REQUEST_CAMERA_PERMISSION_CODE -> CAMERA
            else -> arrayOf() //handle more case later...
        }
}
