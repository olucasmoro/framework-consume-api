package olucasmoro.android.apiconsume.data.network.model

import com.google.gson.annotations.SerializedName
import olucasmoro.android.apiconsume.data.local.model.Album

data class AlbumResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
)

fun AlbumResponse.toDatabaseModel() =
    Album(
        userId = userId,
        id = id,
        title = title
    )