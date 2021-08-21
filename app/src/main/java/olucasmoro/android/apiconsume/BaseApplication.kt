package olucasmoro.android.apiconsume

import android.app.Application
import olucasmoro.android.apiconsume.data.di.dataModules
import olucasmoro.android.apiconsume.data.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(dataModules + retrofitModule)
        }
    }
}