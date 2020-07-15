package minseok.riiidi_homework.presentation.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import minseok.riiidi_homework.R
import minseok.riiidi_homework.presentation.view.base.BaseFragment
import minseok.riiidi_homework.presentation.viewmodel.PostViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_detail

    @Inject lateinit var postViewModel: PostViewModel
    private val arg: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = arg.postId

        val commentAdapter = CommentItemAdapter()
        list_comment.adapter = commentAdapter
        list_comment.layoutManager = LinearLayoutManager(requireContext())

        postViewModel.findPostBy(postId)
            .bind({
                tv_title.text = it.title
                tv_body.text = it.body
            }, {
                it.printStackTrace()
            })

        postViewModel.comments.bind({ comments ->
            commentAdapter.comments = comments

        }, { it.printStackTrace() } )

        postViewModel.loadComments(postId)
    }

}