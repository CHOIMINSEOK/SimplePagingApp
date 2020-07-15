package minseok.riiidi_homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.PostRepository
import minseok.riiidi_homework.domain.Post
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val posts: Observable<List<Post>> = postRepository.getPosts()

    val comments: Observable<List<Comment>> = postRepository.getComments()

    fun findPostBy(id: Int): Observable<Post> {
        return posts.flatMapIterable { it }
            .filter { it.id == id }
    }

    fun loadComments(postId: Int) {
        postRepository.loadComments(postId)
    }

}