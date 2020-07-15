package minseok.riiidi_homework.presentation.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import minseok.riiidi_homework.R
import minseok.riiidi_homework.presentation.view.base.BaseFragment
import minseok.riiidi_homework.presentation.viewmodel.PostViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_list

    @Inject lateinit var postViewModel: PostViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postAdapter = PostItemAdapter(this::handlePostClick)
        list_post.adapter = postAdapter
        list_post.layoutManager = LinearLayoutManager(requireContext())

        postViewModel.posts.subscribe { items ->
            postAdapter.posts = items
        }.collectDisposable()

    }

    private fun handlePostClick(postId: Int) {
        val action = ListFragmentDirections.startDetailFragment(postId)
        this.findNavController().navigate(action)
    }
}