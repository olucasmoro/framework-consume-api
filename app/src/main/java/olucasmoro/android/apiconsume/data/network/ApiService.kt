package olucasmoro.android.apiconsume.data.network

import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumResponse>>

    @GET("todos")
    suspend fun getTodos(): Response<List<TodoResponse>>
}