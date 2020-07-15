package minseok.riiidi_homework.data

import io.reactivex.Observable
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor() : PostRepository {
    override fun getPosts(): Observable<List<Post>> {
        return Observable.just(
            SampleData.getSamplePosts()
        )
    }
}