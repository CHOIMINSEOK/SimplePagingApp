package minseok.riiidi_homework.data.remote

import io.reactivex.Single
import minseok.riiidi_homework.data.remote.model.CommentData
import minseok.riiidi_homework.data.remote.model.PostData
import minseok.riiidi_homework.data.remote.model.PostUpdatePayload
import retrofit2.Call
import retrofit2.http.*

interface PostAPIService {
    @GET("posts")
    suspend fun getPosts(@Query("_start") page: Int, @Query("_limit") limit: Int): List<PostData>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostData

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): List<CommentData>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)

    @PATCH("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body payload: PostUpdatePayload): PostData
}