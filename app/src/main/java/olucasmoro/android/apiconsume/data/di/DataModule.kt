package olucasmoro.android.apiconsume.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import olucasmoro.android.apiconsume.data.local.database.AppDatabase
import olucasmoro.android.apiconsume.data.local.source.LocalData
import olucasmoro.android.apiconsume.data.local.source.LocalDataImpl
import olucasmoro.android.apiconsume.data.network.ApiService
import olucasmoro.android.apiconsume.data.network.source.RemoteData
import olucasmoro.android.apiconsume.data.network.source.RemoteDataImpl
import olucasmoro.android.apiconsume.domain.Repository
import olucasmoro.android.apiconsume.domain.RepositoryImpl
import olucasmoro.android.apiconsume.presenter.BASE_URL
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
    try {
        single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
    } catch (e: Exception) {

    }
}

val database = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().getDao() }
}


val remoteModule = module {
    factory<RemoteData> { RemoteDataImpl(apiService = get()) }
    factory<LocalData> { LocalDataImpl(appDao = get()) }
}

val repositoryModule = module {
    factory<Repository> {
        RepositoryImpl(
            remoteData = get(),
            localData = get()
        )
    }
}

val dataModules = listOf(repositoryModule, remoteModule, database)