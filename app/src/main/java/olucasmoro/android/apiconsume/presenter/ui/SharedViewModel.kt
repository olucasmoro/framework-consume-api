package olucasmoro.android.apiconsume.presenter.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import olucasmoro.android.apiconsume.data.local.Type
import olucasmoro.android.apiconsume.domain.Repository
import olucasmoro.android.apiconsume.domain.TitleRefreshError

class SharedViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _snackBar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackBar

    private val _spinner = MutableLiveData(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    val albums = repository.listAlbums
    val posts = repository.listPosts
    val todos = repository.listTodos

    init {
        launchDataLoad {
            repository.updateList(Type.ALBUM)
            repository.updateList(Type.POST)
            repository.updateList(Type.TODO)
        }
    }

    fun onSnackbarShown() {
        _snackBar.value = null
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: TitleRefreshError) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

}