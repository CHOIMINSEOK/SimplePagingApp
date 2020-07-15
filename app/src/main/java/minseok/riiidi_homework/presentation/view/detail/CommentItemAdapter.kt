package minseok.riiidi_homework.presentation.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import minseok.riiidi_homework.R
import minseok.riiidi_homework.domain.Comment

class CommentItemAdapter: RecyclerView.Adapter<CommentItemViewHolder>() {
    var comments: List<Comment> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        return CommentItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        holder.onBind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.count()
    }
}