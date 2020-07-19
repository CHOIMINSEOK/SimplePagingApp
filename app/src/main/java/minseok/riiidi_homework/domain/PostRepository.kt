package minseok.riiidi_homework.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import minseok.riiidi_homework.data.remote.model.PostUpdatePayload

interface PostRepository {
    fun getPostStreams(): Flow<PagingData<Post>>
    suspend fun getPost(postId: Int): Post
    suspend fun updatePost(postId: Int, payload: PostUpdatePayload)
    suspend fun deletePost(postId: Int)
    suspend fun getComments(postId: Int): List<Comment>
}