package minseok.riiidi_homework.data.remote

import io.reactivex.Single
import minseok.riiidi_homework.data.remote.model.CommentData
import minseok.riiidi_homework.data.remote.model.PostData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostAPIService {
    @GET("posts")
    suspend fun getPosts(@Query("_start") page: Int, @Query("_limit") limit: Int): List<PostData>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostData

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): List<CommentData>
}