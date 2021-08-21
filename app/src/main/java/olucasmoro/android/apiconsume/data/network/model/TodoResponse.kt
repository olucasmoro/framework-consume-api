package olucasmoro.android.apiconsume.data.network.model

import com.google.gson.annotations.SerializedName
import olucasmoro.android.apiconsume.data.local.model.Todo

data class TodoResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean,
)

fun TodoResponse.toDatabaseModel() =
    Todo(
        userId = userId,
        id = id,
        title = title,
        completed = completed
    )