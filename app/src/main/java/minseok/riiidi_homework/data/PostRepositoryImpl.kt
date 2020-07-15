package minseok.riiidi_homework.data

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import minseok.riiidi_homework.data.remote.PostAPIService
import minseok.riiidi_homework.data.remote.PostDataMapper
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPIService: PostAPIService
) : PostRepository {
    override fun getPosts(): Observable<List<Post>> {
        return postAPIService.getPosts(1, 10)
            .subscribeOn(Schedulers.io())
            .map { list -> list.map(PostDataMapper::mapFromImageData)}
            .toObservable()
    }
}