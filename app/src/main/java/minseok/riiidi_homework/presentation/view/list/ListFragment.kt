package minseok.riiidi_homework.presentation.view.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import minseok.riiidi_homework.R
import minseok.riiidi_homework.presentation.view.base.BaseFragment
import minseok.riiidi_homework.presentation.viewmodel.PostViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_list

    @Inject lateinit var postViewModel: PostViewModel

    private val postAdapter = PostItemAdapter(this::handlePostAction)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        list_post.adapter = postAdapter
        list_post.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            postViewModel.posts.collectLatest {
                postAdapter.submitData(it)
            }


        }
    }

    private fun updatePostItem(postId: Int, title: String?, body: String?) {
        lifecycleScope.launch {
            postViewModel.updatePost(postId, title, body)
            postAdapter.refresh()
        }
    }

    private fun deletePostItem(postId: Int) {
        lifecycleScope.launch {
            postViewModel.deletePost(postId)
            postAdapter.refresh()
        }
    }

    private fun handlePostAction(action: Action) {
        when(action) {
            is Action.GoDetail -> {
                this.findNavController().navigate(
                    ListFragmentDirections.startDetailFragment(action.id)
                )
            }

            is Action.UpdatePost -> {
                MaterialDialog(requireContext()).show {
                    customView(R.layout.dialog_update)

                    positiveButton(R.string.dialog_update) { dialog ->
                        val etTitle = dialog.getCustomView().findViewById<EditText>(R.id.et_title)
                        val etBody = dialog.getCustomView().findViewById<EditText>(R.id.et_body)

                        updatePostItem(
                            action.id,
                            if (etTitle.text.isEmpty()) null else etTitle.text.toString(),
                            if (etBody.text.isEmpty()) null else etBody.text.toString()
                        )
                    }
                    negativeButton(R.string.dialog_cancel)
                }
            }

            is Action.DeletePost -> {
                deletePostItem(action.id)
            }
        }
    }
}

sealed class Action {
    class GoDetail(val id: Int): Action()
    class UpdatePost(val id: Int): Action()
    class DeletePost(val id: Int): Action()
}