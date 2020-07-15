package minseok.riiidi_homework.data.remote.model

import com.google.gson.annotations.SerializedName

data class CommentData (
    @SerializedName("postId") val postId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)