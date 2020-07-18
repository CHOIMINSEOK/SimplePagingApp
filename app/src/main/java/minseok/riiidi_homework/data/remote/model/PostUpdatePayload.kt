package minseok.riiidi_homework.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostUpdatePayload (
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?
)