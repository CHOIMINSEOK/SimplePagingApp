package minseok.riiidi_homework.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPostStreams(): Flow<PagingData<Post>>
    suspend fun getPost(postId: Int): Post
    suspend fun deletePost(postId: Int)
    suspend fun getComments(postId: Int): List<Comment>
}