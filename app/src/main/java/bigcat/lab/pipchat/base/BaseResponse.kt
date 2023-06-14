package bigcat.lab.pipchat.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author hoantd on 8/23/22
 **/
@Serializable
data class BaseResponse(
    @SerialName("message") val message: String? = ""
)
