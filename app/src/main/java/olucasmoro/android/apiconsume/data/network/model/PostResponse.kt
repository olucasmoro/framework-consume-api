package olucasmoro.android.apiconsume.data.network.model

import com.google.gson.annotations.SerializedName
import olucasmoro.android.apiconsume.data.local.model.Post

data class PostResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

fun PostResponse.toDatabaseModel() =
    Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )