package co.effectstudios.resplash.presentation.ui.list

import co.effectstudios.resplash.R
import co.effectstudios.resplash.core.GlideApp
import co.effectstudios.resplash.data.local.entity.AuthorEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_layout_authors.*

class AuthorItem(val authorEntry: AuthorEntry) : Item() {
    override fun getLayout() = R.layout.item_layout_authors

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            GlideApp.with(this.containerView)
                .load(authorEntry.downloadUrl)
                .into(iv_author_image)
            tv_author_name.text = authorEntry.author
        }
    }
}