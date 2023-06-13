package bigcat.lab.baseappkotlin.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author mvn-toan.nguyen2 on 8/23/22
 **/
@Serializable
data class BaseResponse(
    @SerialName("message") val message: String? = ""
)
