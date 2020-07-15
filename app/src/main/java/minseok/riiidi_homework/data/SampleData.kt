package minseok.riiidi_homework.data

import minseok.riiidi_homework.domain.Post

object SampleData {
    fun getSamplePosts(): List<Post> {
        return listOf(
            Post(1, "Post1 Title", "Post1 Body"),
            Post(2, "Post2 Title", "Post2 Body"),
            Post(3, "Post3 Title", "Post3 Body"),
            Post(4, "Post4 Title", "Post4 Body"),
            Post(5, "Post5 Title", "Post5 Body"),
            Post(6, "Post6 Title", "Post6 Body"),
            Post(7, "Post7 Title", "Post7 Body"),
            Post(8, "Post8 Title", "Post8 Body")
        )
    }
}