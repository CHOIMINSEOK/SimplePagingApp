package minseok.riiidi_homework.data.remote

import minseok.riiidi_homework.data.remote.model.CommentData
import minseok.riiidi_homework.data.remote.model.PostData
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.Post

object DataMapper {
    fun mapFromPostDataToPost(postData: PostData): Post {
        return Post(
            postData.id,
            postData.title,
            postData.body
        )
    }

    fun mapFromCommentDataToComment(commentData: CommentData): Comment {
        return Comment(
            commentData.postId,
            commentData.id,
            commentData.email,
            commentData.body
        )
    }
}