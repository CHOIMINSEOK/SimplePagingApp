package minseok.riiidi_homework.presentation.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import minseok.riiidi_homework.domain.Post

class PostItemViewHolder(
    view: View,
    private val block: (Action) -> Unit
): RecyclerView.ViewHolder(view) {
    fun onBind(post: Post?) {
        with(itemView) {
            title.text = post?.title
            body.text = post?.body

            setOnClickListener {
                post?.let {
                    block.invoke(Action.GoDetail(it.id))
                }
            }

             post?.let {
                 setOnCreateContextMenuListener { contextMenu, view, _ ->
                     contextMenu?.apply {
                         add(0, view.id, 0, "수정하기").setOnMenuItemClickListener {
                             block.invoke(Action.UpdatePost(post.id, absoluteAdapterPosition))
                             true
                         }
                         add(0, view.id, 0, "삭제하기").setOnMenuItemClickListener {
                             block.invoke(Action.DeletePost(post.id, absoluteAdapterPosition))
                             true
                         }
                     }
                 }

             }
        }
    }

}