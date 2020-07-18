package minseok.riiidi_homework.presentation.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import minseok.riiidi_homework.R
import minseok.riiidi_homework.domain.Post

class PostItemAdapter(
    val block: (Int) -> Unit
): PagingDataAdapter<Post, PostItemViewHolder>(POST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false),
            block
        )
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}