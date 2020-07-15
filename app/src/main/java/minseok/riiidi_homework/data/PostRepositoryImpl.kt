package minseok.riiidi_homework.data

import io.reactivex.Observable
import minseok.riiidi_homework.app.SchedulerProvider
import minseok.riiidi_homework.data.remote.PostAPIService
import minseok.riiidi_homework.data.remote.PostDataMapper
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPIService: PostAPIService,
    private val schedulerProvider: SchedulerProvider
) : PostRepository {
    override fun getPosts(): Observable<List<Post>> {
        return postAPIService.getPosts(1, 10)
            .subscribeOn(schedulerProvider.io())
            .map { list -> list.map(PostDataMapper::mapFromImageData)}
            .toObservable()
    }
}