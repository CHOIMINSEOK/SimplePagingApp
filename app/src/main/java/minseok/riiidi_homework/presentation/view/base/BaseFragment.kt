package minseok.riiidi_homework.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment: Fragment() {
    abstract val layoutRes: Int

    private val compositeDisposable = CompositeDisposable()

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

    fun Disposable.collectDisposable() { compositeDisposable.add(this) }
}