package bigcat.lab.pipchat.domain.repository.shared

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Environment
import bitcat.lab.pipchat.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/*
* Created by ducnguyen on 31/05/2022.
*/
@Singleton
class SharedPreferenceRepository @Inject constructor(application: Application) {
    private val sharedPreferences = application.getSharedPreferences(null, Context.MODE_PRIVATE)
    private val context: Context = application.applicationContext

    fun getTokenUser() = sharedPreferences.getString(KEY_TOKEN_USER, "")

    /**
     * This func used to create file .jpg before save
     */
    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val imageFileName = String.format(
            "${FILE_NAME}_%s",
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        )

        val file = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            Environment.getExternalStoragePublicDirectory(
                "${Environment.DIRECTORY_DCIM}/${
                    context.getString(
                        R.string.app_namesss
                    )
                }"
            )
        else Environment.getExternalStoragePublicDirectory(context.getString(R.string.app_namesss))

        with(file) {
            this.mkdirs()
            return File.createTempFile(
                imageFileName, /*prefix */
                ".jpg", /*suffix */
                this /*directory */
            )
        }
    }

    companion object {
        private const val FILE_NAME = "HealthyLifeStyle"
        private const val KEY_TOKEN_USER = "key_token_user"
    }
}
