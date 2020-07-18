package minseok.riiidi_homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.PostRepository
import minseok.riiidi_homework.domain.Post
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val posts: Flow<PagingData<Post>> = postRepository.getPostStreams().cachedIn(viewModelScope)

    suspend fun findPostBy(id: Int): Post {
        return postRepository.getPost(id)
    }

    suspend fun deletePost(id: Int) {
        postRepository.deletePost(id)
    }

    suspend fun loadComments(postId: Int):  List<Comment> {
        return postRepository.getComments(postId)
    }

}