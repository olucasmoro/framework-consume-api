package olucasmoro.android.apiconsume.presenter.di

import olucasmoro.android.apiconsume.presenter.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SharedViewModel(get()) }
}

val presenterModules = listOf(viewModelModule)