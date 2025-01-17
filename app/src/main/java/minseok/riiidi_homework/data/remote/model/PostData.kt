package minseok.riiidi_homework.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)