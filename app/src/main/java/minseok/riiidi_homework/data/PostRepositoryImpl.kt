package minseok.riiidi_homework.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import minseok.riiidi_homework.data.remote.DataMapper
import minseok.riiidi_homework.data.remote.PostAPIService
import minseok.riiidi_homework.data.remote.PostDataPagingSource
import minseok.riiidi_homework.data.remote.model.PostUpdatePayload
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPIService: PostAPIService
) : PostRepository {
    private val cachedPostList: ArrayList<Post> = arrayListOf()
    private lateinit var dataSource: PostDataPagingSource

    override fun getPostStreams(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dataSource = PostDataPagingSource(this::getPosts, cachedPostList.toList())
                dataSource
            }
        ).flow
    }

    suspend fun getPosts(page: Int, limit: Int): List<Post> {
        val posts = postAPIService.getPosts(page, limit).map(DataMapper::mapFromPostDataToPost)
        cachedPostList.addAll(posts)
        return posts
    }


    override suspend fun getComments(postId: Int): List<Comment> {
        return postAPIService.getComments(postId).map(DataMapper::mapFromCommentDataToComment)
    }

    override suspend fun getPost(postId: Int): Post {
        return DataMapper.mapFromPostDataToPost(postAPIService.getPost(postId))
    }

    override suspend fun updatePost(postId: Int, payload: PostUpdatePayload){
        val updatePost = DataMapper.mapFromPostDataToPost(postAPIService.updatePost(postId, payload))
        cachedPostList.replaceAll {
            if (it.id == postId)
                Post(it.id, updatePost.title, updatePost.body)
            else it
        }
        dataSource.invalidate()
    }

    override suspend fun deletePost(postId: Int) {
        postAPIService.deletePost(postId)
        cachedPostList.removeIf { it.id == postId }
        dataSource.invalidate()
    }
}