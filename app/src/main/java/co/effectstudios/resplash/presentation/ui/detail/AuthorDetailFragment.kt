package co.effectstudios.resplash.presentation.ui.detail

import android.os.Bundle
import android.view.View
import co.effectstudios.resplash.R
import co.effectstudios.resplash.core.GlideApp
import co.effectstudios.resplash.presentation.common.ScopedFragment
import kotlinx.android.synthetic.main.author_details_fragment.*

class AuthorDetailFragment : ScopedFragment(R.layout.author_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() {

        val safeArgs = arguments?.let { AuthorDetailFragmentArgs.fromBundle(it) }
        val author = safeArgs?.authorName ?: "N/A"
        val image = safeArgs?.imageUrl ?: "N/A"

        GlideApp.with(this@AuthorDetailFragment)
            .load(image)
            .into(iv_author_image)
        tv_author_name.text = author
    }

}