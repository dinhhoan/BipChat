package bigcat.lab.baseappkotlin.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchToEarnRequest(
    @SerialName("watched_time") val watchedTime: Long,
    @SerialName("hashkey") val hashKey: String
)
