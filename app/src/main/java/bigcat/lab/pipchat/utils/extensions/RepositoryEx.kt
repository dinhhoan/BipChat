package bizverse.lab.healthylifestyle.utils.extensions

import retrofit2.Response

/**
 * @author mvn-toan.nguyen2 on 8/23/22
 **/
inline fun <T> apiCall(
    block: () -> Response<T>
): T {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> body
        false -> throw response.toError()
    }
}

