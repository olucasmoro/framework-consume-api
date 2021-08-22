package olucasmoro.android.apiconsume.data.network

import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse
import olucasmoro.android.apiconsume.presenter.POSTS
import olucasmoro.android.apiconsume.presenter.ALBUMS
import olucasmoro.android.apiconsume.presenter.TODOS
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(POSTS)
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET(ALBUMS)
    suspend fun getAlbums(): Response<List<AlbumResponse>>

    @GET(TODOS)
    suspend fun getTodos(): Response<List<TodoResponse>>
}