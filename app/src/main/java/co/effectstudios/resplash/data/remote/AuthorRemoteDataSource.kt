package co.effectstudios.resplash.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.effectstudios.resplash.core.NoConnectivityException
import co.effectstudios.resplash.data.remote.response.AuthorResponse
import timber.log.Timber
import javax.inject.Inject

class AuthorRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    private val _downloadedAuthors = MutableLiveData<List<AuthorResponse>>()
    val downloadedAuthors: LiveData<List<AuthorResponse>>
        get() = _downloadedAuthors

    suspend fun fetchAuthors() {
        try {

            val fetchedAuthors = apiService.getAuthors().await()
            _downloadedAuthors.postValue(fetchedAuthors)

        } catch (e: NoConnectivityException) {
            Timber.e(e, "No internet connection.")
        }
    }

}