package minseok.riiidi_homework.presentation.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.launch
import minseok.riiidi_homework.R
import minseok.riiidi_homework.presentation.view.base.BaseFragment
import minseok.riiidi_homework.presentation.viewmodel.PostViewModel
import java.lang.Error
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

        lifecycleScope.launch {
            try {
                val post = postViewModel.findPostBy(postId)
                tv_title.text = post.title
                tv_body.text = post.body

                val comments = postViewModel.loadComments(postId)
                commentAdapter.comments = comments
            } catch (e: Error) {
                e.printStackTrace()
            }
        }
    }

}