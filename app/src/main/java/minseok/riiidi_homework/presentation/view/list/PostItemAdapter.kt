package minseok.riiidi_homework.presentation.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import minseok.riiidi_homework.R
import minseok.riiidi_homework.domain.Post

class PostItemAdapter(
    val block: (Int) -> Unit
): RecyclerView.Adapter<PostItemViewHolder>() {
    var posts: List<Post> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false),
            block
        )
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.onBind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.count()
    }
}