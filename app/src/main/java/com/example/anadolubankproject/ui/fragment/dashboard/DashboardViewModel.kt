package com.example.anadolubankproject.ui.fragment.dashboard

import androidx.lifecycle.MutableLiveData
import com.example.anadolubankproject.model.dashboard.PostsResponse
import com.example.anadolubankproject.service.DashboradService
import com.example.anadolubankproject.ui.base.SingleLiveEvent
import com.example.anadolubankproject.ui.base.State
import com.example.anadolubankproject.ui.base.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DashboardViewModel @Inject constructor(private val dashboardService: DashboradService) :
    BaseViewModel() {

    val data = MutableLiveData<PostsResponse>()
    val error: MutableLiveData<Throwable> = SingleLiveEvent()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getList() {
        applyDisposable(dashboardService.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { state.postValue(State.loading(Pair(true, second = true))) }
            .subscribeWith(object : DisposableSingleObserver<PostsResponse>() {
                override fun onSuccess(t: PostsResponse) {
                    loading.postValue(false)
                    data.postValue(t)
                }

                override fun onError(e: Throwable) {
                    loading.postValue(false)
                    error.postValue(e)
                }
            })
        )
    }
}