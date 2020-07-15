package minseok.riiidi_homework.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import minseok.riiidi_homework.app.SchedulerProvider
import javax.inject.Inject

abstract class BaseFragment: Fragment() {
    abstract val layoutRes: Int

    private val compositeDisposable = CompositeDisposable()
    @Inject lateinit var schedulerProvider: SchedulerProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    fun <T> Observable<T>.bind(onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
        return this.doOnSubscribe {
            compositeDisposable.add(it)
        }.observeOn(schedulerProvider.ui())
            .subscribe(onNext, onError)
    }
}