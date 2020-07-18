package minseok.riiidi_homework.presentation.view.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postAdapter = PostItemAdapter(this::handlePostClick)
        list_post.adapter = postAdapter
        list_post.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            postViewModel.posts.collectLatest {
                postAdapter.submitData(it)
            }
        }
    }

    private fun handlePostClick(postId: Int) {
        val action = ListFragmentDirections.startDetailFragment(postId)
        this.findNavController().navigate(action)
    }
}