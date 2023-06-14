package bigcat.lab.pipchat.data.error

import bigcat.lab.pipchat.data.error.ErrorModel.ApiErrorDetailCode.*
import java.net.HttpURLConnection

/**
 * @author hoantd on 8/23/22
 **/
sealed class ErrorModel : Throwable() {
    open fun isCommonError(): Boolean = false
    open fun isLocalError(): Boolean = false

    companion object {
        const val API_ERROR_RESULT_CODE = "1"
    }

    sealed class Http : ErrorModel() {
        data class ApiError(
            val code: String?,
            override val message: String?,
            val apiUrl: String?
        ) : Http() {
            override fun isCommonError(): Boolean {
                if (code == HttpURLConnection.HTTP_UNAUTHORIZED.toString()
                    || code == HttpURLConnection.HTTP_INTERNAL_ERROR.toString()
                    || code == SERVER_MAINTENANCE_9001.code
                    || code == FORCE_UPDATE_8001.code
                    || code == NO_RESPONSE_FROM_SERVER_9002.code
                ) {
                    return true
                }
                return false
            }
        }
    }

    data class LocalError(val errorMessage: String, val code: String?) : ErrorModel() {
        override fun isLocalError(): Boolean {
            return true
        }
    }

    enum class LocalErrorException(val message: String, val code: String) {
        NO_INTERNET_EXCEPTION(
            "No internet connection.\nPlease check your internet connection and try again.",
            "1001"
        ),
        REQUEST_TIME_OUT_EXCEPTION(
            "Request timed out.\nPlease check your internet connection and try again.",
            "1002"
        ),
        UN_KNOW_EXCEPTION("Error. Please try again later.", "1000")
    }

    enum class ApiErrorDetailCode(val code: String) {
        SERVER_MAINTENANCE_9001("9001"),
        FORCE_UPDATE_8001("8001"),
        NO_RESPONSE_FROM_SERVER_9002("9002")
    }
}
