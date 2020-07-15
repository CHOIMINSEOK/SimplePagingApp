package minseok.riiidi_homework.domain

import io.reactivex.Observable

interface PostRepository {
    fun getPosts(): Observable<List<Post>>
    fun getComments(): Observable<List<Comment>>
    fun loadComments(postId: Int)
}