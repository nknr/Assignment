package com.neeraj.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neeraj.assignment.data.remote.Resource
import com.neeraj.assignment.data.repository.PostRepository
import com.neeraj.assignment.data.room.entry.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _postListLiveData: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val postListLiveData: LiveData<Resource<List<Post>>> = _postListLiveData
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading


    init {
        getPostList()
    }


    private fun getPostList() {
        _loading.value = true
        viewModelScope.launch {
            _postListLiveData.postValue(repository.getPostList())
            _loading.postValue(false)
        }
    }

    companion object {
        private const val TAG = "PostViewModel"
    }
}