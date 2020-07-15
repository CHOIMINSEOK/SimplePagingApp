package minseok.riiidi_homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import minseok.riiidi_homework.domain.PostRepository
import minseok.riiidi_homework.domain.Post
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val posts: Observable<List<Post>> = postRepository.getPosts()

    fun findPostBy(id: Int): Observable<Post> {
        return posts.flatMapIterable { it }
            .filter { it.id == id }
    }
}