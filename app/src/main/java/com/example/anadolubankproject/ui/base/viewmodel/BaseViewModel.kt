package com.example.anadolubankproject.ui.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anadolubankproject.ui.base.SingleLiveEvent
import com.example.anadolubankproject.ui.base.State
import com.example.anadolubankproject.ui.base.interfaces.IBaseDisposable
import com.example.anadolubankproject.ui.base.interfaces.IBaseSubscriber
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), IBaseDisposable {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }
    val state: MutableLiveData<State> = SingleLiveEvent()

    override fun clearDispos() {
        disposables.clear()
    }

    override fun applyDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        clearDispos()
        disposables.dispose()
    }
}