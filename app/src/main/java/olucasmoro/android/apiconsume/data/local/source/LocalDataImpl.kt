package olucasmoro.android.apiconsume.data.local.source

import androidx.lifecycle.LiveData
import olucasmoro.android.apiconsume.data.local.database.AppDao
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.data.local.model.Todo
import olucasmoro.android.apiconsume.data.local.Type

class LocalDataImpl(
    private val appDao: AppDao,
) : LocalData {

    override suspend fun <T> updateDatabaseList(list: List<T>, type: Type) {
        deleteDatabaseList(type).also {
            when (type) {
                Type.ALBUM -> appDao.insertAllAlbum(list as List<Album>)
                Type.TODO -> appDao.insertAllTodo(list as List<Todo>)
                Type.POST -> appDao.insertAllPosts(list as List<Post>)
            }
        }
    }

    private suspend fun deleteDatabaseList(type: Type) {
        when (type) {
            Type.ALBUM -> appDao.deleteAllAlbum()
            Type.TODO -> appDao.deleteAllTodo()
            Type.POST -> appDao.deleteAllPosts()
        }
    }

    override fun <T> getAllItems(type: Type): LiveData<List<T>> =
        appDao.getAllItems(type)

}