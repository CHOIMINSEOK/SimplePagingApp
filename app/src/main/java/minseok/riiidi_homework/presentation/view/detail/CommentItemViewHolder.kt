package minseok.riiidi_homework.presentation.view.detail

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_comment.view.*
import minseok.riiidi_homework.domain.Comment

class CommentItemViewHolder (
    view: View
): RecyclerView.ViewHolder(view) {
    fun onBind(comment: Comment) {
        with(itemView) {
            tv_email.text = comment.email
            tv_body.text = comment.body


        }
    }
}