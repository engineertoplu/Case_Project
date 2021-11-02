package com.example.anadolubankproject.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anadolubankproject.ui.base.interfaces.IBaseSubscriber
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), IBaseSubscriber {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }
    val state: MutableLiveData<State> = MutableLiveData()

    override fun unSubscribe() {
        disposables.clear()
    }

    override fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        unSubscribe()
    }
}