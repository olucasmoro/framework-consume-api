package olucasmoro.android.apiconsume.data.local.source

import androidx.lifecycle.LiveData
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.data.local.model.Todo
import olucasmoro.android.apiconsume.data.local.Type

interface LocalData {

    suspend fun <T> updateDatabaseList(list: List<T>, type: Type)

    fun <T> getAllItems(type: Type) : LiveData<List<T>>

}