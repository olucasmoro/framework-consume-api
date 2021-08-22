package olucasmoro.android.apiconsume.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.coroutineScope
import olucasmoro.android.apiconsume.data.local.Type
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.data.local.model.Todo
import olucasmoro.android.apiconsume.data.local.source.LocalData
import olucasmoro.android.apiconsume.data.network.ApiResult
import olucasmoro.android.apiconsume.data.network.RequestHandler.makeRequest
import olucasmoro.android.apiconsume.data.network.model.AlbumResponse
import olucasmoro.android.apiconsume.data.network.model.PostResponse
import olucasmoro.android.apiconsume.data.network.model.TodoResponse
import olucasmoro.android.apiconsume.data.network.model.toDatabaseModel
import olucasmoro.android.apiconsume.data.network.source.RemoteData
import retrofit2.Response

class RepositoryImpl(
    private val remoteData: RemoteData,
    private val localData: LocalData,
) : Repository {

    override val listAlbums: LiveData<List<Album>> = localData.getAllItems(Type.ALBUM)
    override val listPosts: LiveData<List<Post>> = localData.getAllItems(Type.POST)
    override val listTodos: LiveData<List<Todo>> = localData.getAllItems(Type.TODO)

    override suspend fun updateList(type: Type) {
        when (type) {
            Type.ALBUM -> updateDatabase(remoteData::getAlbums, Type.ALBUM)
            Type.POST -> updateDatabase(remoteData::getPosts, Type.POST)
            Type.TODO -> updateDatabase(remoteData::getTodos, Type.TODO)
        }
    }

    private suspend fun <T> updateDatabase(
        requestCallback: suspend () -> Response<List<T>>,
        type: Type,
    ) {
        makeRequest { requestCallback() }.also { apiResult ->
            when (apiResult) {
                is ApiResult.Success ->
                    coroutineScope {
                        when (type) {
                            Type.ALBUM -> {
                                localData.updateDatabaseList(
                                    apiResult.data.map { (it as AlbumResponse).toDatabaseModel() },
                                    type
                                )
                            }
                            Type.POST -> {
                                localData.updateDatabaseList(
                                    apiResult.data.map { (it as PostResponse).toDatabaseModel() },
                                    type
                                )
                            }
                            Type.TODO -> {
                                localData.updateDatabaseList(
                                    apiResult.data.map { (it as TodoResponse).toDatabaseModel() },
                                    type
                                )
                            }
                        }
                    }
                is ApiResult.Error -> {
                }
            }
        }
    }
}

class TitleRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)