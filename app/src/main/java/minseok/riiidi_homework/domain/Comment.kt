package minseok.riiidi_homework.domain

data class Comment(
    val postId: Int,
    val id: Int,
    val email: String,
    val body: String
)