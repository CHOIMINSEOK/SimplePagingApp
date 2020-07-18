package minseok.riiidi_homework.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import minseok.riiidi_homework.data.remote.PostAPIService
import minseok.riiidi_homework.data.remote.DataMapper
import minseok.riiidi_homework.data.remote.PostDataPagingSource
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPIService: PostAPIService
) : PostRepository {
    override fun getPostStreams(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PostDataPagingSource(postAPIService) }
        ).flow
    }


    override suspend fun getComments(postId: Int): List<Comment> {
        return postAPIService.getComments(postId).map(DataMapper::mapFromCommentDataToComment)
    }

    override suspend fun getPost(postId: Int): Post {
        return DataMapper.mapFromPostDataToPost(postAPIService.getPost(postId))
    }

}