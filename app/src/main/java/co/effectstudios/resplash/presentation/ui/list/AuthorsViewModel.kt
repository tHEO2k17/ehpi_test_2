package co.effectstudios.resplash.presentation.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import co.effectstudios.resplash.core.Delegates.lazyDeferred
import co.effectstudios.resplash.data.repositories.AuthorRepository

class AuthorsViewModel @ViewModelInject constructor(
    private val authorRepository: AuthorRepository
) : ViewModel() {

    val authors by lazyDeferred {
        authorRepository.getAuthors()
    }
}