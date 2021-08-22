package olucasmoro.android.apiconsume.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.data.local.model.Todo

@Database(entities = [Album::class, Post::class, Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): AppDao

    companion object {
        private const val DB_NAME = "framework.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}