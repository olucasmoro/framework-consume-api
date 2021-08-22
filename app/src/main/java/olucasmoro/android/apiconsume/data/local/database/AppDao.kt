package olucasmoro.android.apiconsume.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import olucasmoro.android.apiconsume.data.local.Type
import olucasmoro.android.apiconsume.data.local.model.*
import olucasmoro.android.apiconsume.data.network.model.AlbumResponse

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbum(posts: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodo(posts: List<Todo>)

    @Query("DELETE FROM post")
    suspend fun deleteAllPosts()

    @Query("DELETE FROM album")
    suspend fun deleteAllAlbum()

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodo()

    @Query("SELECT * FROM album")
    fun getAllAlbum(): LiveData<List<Album>>

    @Query("SELECT * FROM post")
    fun getAllPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM todo")
    fun getAllTodo(): LiveData<List<Todo>>

    fun <T> getAllItems(type: Type): LiveData<List<T>> {
        return when (type) {
            Type.ALBUM -> getAllAlbum() as LiveData<List<T>>
            Type.POST -> getAllPosts() as LiveData<List<T>>
            Type.TODO -> getAllTodo() as LiveData<List<T>>
        }
    }

}