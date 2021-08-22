package olucasmoro.android.apiconsume.data.network.source

import olucasmoro.android.apiconsume.data.network.ApiService
import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse
import retrofit2.Response

class RemoteDataImpl(
    private val apiService: ApiService,
) : RemoteData {

    override suspend fun getPosts(): Response<List<PostResponse>> =
        apiService.getPosts()

    override suspend fun getAlbums(): Response<List<AlbumResponse>> =
        apiService.getAlbums()

    override suspend fun getTodos(): Response<List<TodoResponse>> =
        apiService.getTodos()

}