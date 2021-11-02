package com.example.anadolubankproject.ui.fragment.detail

import androidx.lifecycle.MutableLiveData
import com.example.anadolubankproject.model.dashboard.PostsResponseItem
import com.example.anadolubankproject.ui.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(): BaseViewModel() {

    val data = MutableLiveData<PostsResponseItem>()
}