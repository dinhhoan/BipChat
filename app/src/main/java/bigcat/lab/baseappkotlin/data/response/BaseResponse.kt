package bigcat.lab.baseappkotlin.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author mvn-toan.nguyen2 on 8/31/22
 **/
@Serializable
open class BaseResponse(
    @SerialName("status")
    var status: Int? = null,
    @SerialName("message")
    var message: String? = null
)
