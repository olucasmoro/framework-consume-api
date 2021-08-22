package olucasmoro.android.apiconsume.data.network

import kotlinx.coroutines.coroutineScope
import olucasmoro.android.apiconsume.presenter.CONNECTION_FAIL
import retrofit2.Response

object RequestHandler {
    suspend fun <T> makeRequest(block: suspend () -> Response<T>): ApiResult<T> {
        return coroutineScope {
            try {
                block().run {
                    if (isSuccessful) {
                        ApiResult.Success(body())
                    } else {
                        ApiResult.Error(
                            Exception(
                                CONNECTION_FAIL
                            )
                        )
                    }
                }
            } catch (t: Exception) {
                ApiResult.Error(t)
            } as ApiResult<T>
        }
    }
}