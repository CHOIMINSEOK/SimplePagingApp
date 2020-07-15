package minseok.riiidi_homework.presentation.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import minseok.riiidi_homework.domain.Post

class PostItemViewHolder(
    view: View,
    private val block: (Int) -> Unit
): RecyclerView.ViewHolder(view) {
    fun onBind(post: Post) {
        with(itemView) {
            title.text = post.title
            body.text = post.body

            setOnClickListener {
                block.invoke(post.id)
            }
        }
    }
}