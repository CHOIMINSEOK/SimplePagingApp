package minseok.riiidi_homework.data.remote

import androidx.paging.PagingSource
import minseok.riiidi_homework.domain.Post
import retrofit2.HttpException
import java.io.IOException
import kotlin.reflect.KSuspendFunction2

private const val STARTING_PAGE_INDEX = 1

class PostDataPagingSource(
    private val loadPosts: KSuspendFunction2<Int, Int, List<Post>>,
    private val cachedPostList: List<Post>
): PagingSource<Int, Post>() {

    var initData = false

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val result = LoadResult.Page(
                data = if(cachedPostList.isNotEmpty() && !initData) cachedPostList else loadPosts(position, params.loadSize) ,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = position + 1
            )

            initData = true
            return result
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}