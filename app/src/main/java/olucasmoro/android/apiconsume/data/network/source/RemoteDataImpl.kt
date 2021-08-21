package olucasmoro.android.apiconsume.data.network.source

import olucasmoro.android.apiconsume.data.network.ApiService
import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse

class RemoteDataImpl(
    private val apiService: ApiService
) : RemoteData {
    override suspend fun getPosts(): List<PostResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbums(): List<AlbumResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getTodos(): List<TodoResponse> {
        TODO("Not yet implemented")
    }
}