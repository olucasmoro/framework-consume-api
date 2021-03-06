package olucasmoro.android.apiconsume.domain

import androidx.lifecycle.LiveData
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.data.local.model.Todo
import olucasmoro.android.apiconsume.data.local.Type

interface Repository {

    val listAlbums: LiveData<List<Album>>
    val listPosts: LiveData<List<Post>>
    val listTodos: LiveData<List<Todo>>

    suspend fun updateList(type: Type)
}