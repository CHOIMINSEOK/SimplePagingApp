package minseok.riiidi_homework.presentation.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.*
import minseok.riiidi_homework.R
import minseok.riiidi_homework.presentation.view.base.BaseFragment
import minseok.riiidi_homework.presentation.viewmodel.PostViewModel

class DetailFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_detail

    private val postViewModel by activityViewModels<PostViewModel>()
    private val arg: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = arg.postId

        postViewModel.findPostBy(postId)?.let {
            tv_title.text = it.title
            tv_body.text = it.body
        }

    }

}