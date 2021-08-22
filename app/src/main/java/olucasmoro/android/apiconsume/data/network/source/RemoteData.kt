package olucasmoro.android.apiconsume.data.network.source

import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse
import retrofit2.Response

interface RemoteData {

    suspend fun getPosts(): Response<List<PostResponse>>

    suspend fun getAlbums(): Response<List<AlbumResponse>>

    suspend fun getTodos(): Response<List<TodoResponse>>
}