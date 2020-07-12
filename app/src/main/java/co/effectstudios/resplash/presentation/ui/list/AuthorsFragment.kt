package co.effectstudios.resplash.presentation.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import co.effectstudios.resplash.R
import co.effectstudios.resplash.core.GlideApp
import co.effectstudios.resplash.data.local.entity.AuthorEntry
import co.effectstudios.resplash.presentation.common.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.author_details_fragment.*
import kotlinx.android.synthetic.main.authors_fragment.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthorsFragment : ScopedFragment(R.layout.authors_fragment) {
    private val viewModel: AuthorsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() = launch {
        val authors = viewModel.authors.await()

        authors.observe(viewLifecycleOwner, Observer { authors ->
            if (authors == null) return@Observer

            pg_loader.visibility = View.GONE

            showContent()

            val authItems = authors.toAuthorItems()

            initAuthorRecyclerView(authItems)
            GlideApp.with(this@AuthorsFragment)
                .load(authItems.last().authorEntry.downloadUrl)
                .into(iv_image_of_the_week)
            iv_image_of_the_week.setOnClickListener { view ->
                showAuthorDetail(authItems.last(), view)
            }

        })
    }

    private fun showContent(){
        titleTextView.visibility = View.VISIBLE
        cv_image_of_the_week.visibility = View.VISIBLE
        lv_authors.visibility = View.VISIBLE
    }

    private fun List<AuthorEntry>.toAuthorItems(): List<AuthorItem> {
        return this.map { AuthorItem(it) }
    }

    private fun initAuthorRecyclerView(items: List<AuthorItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        rc_authors_list_view.apply {
            layoutManager = LinearLayoutManager(
                this@AuthorsFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? AuthorItem)?.let {
                showAuthorDetail(it, view)
            }
        }

    }

    private fun showAuthorDetail(item: AuthorItem, view: View) {
        val authorDetail = AuthorsFragmentDirections.toAuthorDetails(
            imageUrl = item.authorEntry.downloadUrl,
            authorName = item.authorEntry.author
        )
        Navigation.findNavController(view).navigate(authorDetail)
    }

}