package minseok.riiidi_homework.data.remote

import androidx.paging.PagingSource
import minseok.riiidi_homework.domain.Post
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PostDataPagingSource(
    private val postAPIService: PostAPIService
): PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = postAPIService.getPosts(position, params.loadSize)
            LoadResult.Page(
                data = response.map(DataMapper::mapFromPostDataToPost),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}