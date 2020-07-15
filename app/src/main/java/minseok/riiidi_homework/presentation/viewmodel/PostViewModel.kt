package minseok.riiidi_homework.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import minseok.riiidi_homework.data.SampleData
import minseok.riiidi_homework.domain.Post

class PostViewModel: ViewModel() {
    private val _posts: MutableLiveData<List<Post>> = MutableLiveData<List<Post>>()

    val posts: LiveData<List<Post>> = _posts

    fun loadPosts() {
        _posts.value = SampleData.getSamplePosts()
    }

    fun findPostBy(id: Int): Post? {
        return SampleData.getSamplePosts().find { it.id == id }
    }
}