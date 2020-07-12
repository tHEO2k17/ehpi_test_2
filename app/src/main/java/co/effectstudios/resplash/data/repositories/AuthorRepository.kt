package co.effectstudios.resplash.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import co.effectstudios.resplash.data.remote.AuthorRemoteDataSource
import co.effectstudios.resplash.data.local.AuthorDao
import co.effectstudios.resplash.data.local.entity.AuthorEntry
import co.effectstudios.resplash.data.remote.response.AuthorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject

class AuthorRepository @Inject constructor(
    private val authorDao: AuthorDao,
    private val authorRemoteDataSource: AuthorRemoteDataSource
) {

    init {
        authorRemoteDataSource
            .downloadedAuthors
            .observeForever { persistFetchedAuthors(it) }
    }

    suspend fun getAuthors(): LiveData<out List<AuthorEntry>> {
        return withContext(Dispatchers.IO) {
            initAuthorData()
            return@withContext authorDao.getAllAuthors()
        }
    }

    private fun persistFetchedAuthors(fetchedAuthor: List<AuthorResponse>) {
        GlobalScope.launch(Dispatchers.IO) {

            val toAuthorEntry = fetchedAuthor.map { it ->
                AuthorEntry(
                    author = it.author,
                    downloadUrl = it.downloadUrl,
                    height = it.height,
                    id = it.id,
                    url = it.url,
                    width = it.width
                )
            }

            authorDao.addAllAuthors(toAuthorEntry)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initAuthorData() {
        if (isFetchRequired(ZonedDateTime.now().minusHours(1)))
            fetchAuthors()
    }

    private suspend fun fetchAuthors() {
        authorRemoteDataSource.fetchAuthors()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchRequired(lastFetched: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetched.isBefore(thirtyMinutesAgo)
    }

}