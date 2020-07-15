package minseok.riiidi_homework.data

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import minseok.riiidi_homework.app.SchedulerProvider
import minseok.riiidi_homework.data.remote.PostAPIService
import minseok.riiidi_homework.data.remote.DataMapper
import minseok.riiidi_homework.domain.Comment
import minseok.riiidi_homework.domain.Post
import minseok.riiidi_homework.domain.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPIService: PostAPIService,
    private val schedulerProvider: SchedulerProvider
) : PostRepository {
    private val postIdSubject: PublishSubject<Int> = PublishSubject.create()
    private val commentObservable: Observable<List<Comment>> =
        postIdSubject.switchMapSingle {
            postAPIService.getComments(it)
                .subscribeOn(schedulerProvider.io())
                .map { list -> list.map(DataMapper::mapFromCommentDataToComment) }
        }

    override fun getPosts(): Observable<List<Post>> {
        return postAPIService.getPosts(1, 10)
            .subscribeOn(schedulerProvider.io())
            .map { list -> list.map(DataMapper::mapFromPostDataToPost)}
            .toObservable()
    }


    override fun getComments(): Observable<List<Comment>> {
        return commentObservable
    }

    override fun loadComments(postId: Int) {
        postIdSubject.onNext(postId)
    }

}