package minseok.riiidi_homework.data.remote

import minseok.riiidi_homework.data.remote.model.PostData
import minseok.riiidi_homework.domain.Post

object PostDataMapper {
    fun mapFromImageData(postData: PostData): Post {
        return Post(
            postData.id,
            postData.title,
            postData.body
        )
    }
}