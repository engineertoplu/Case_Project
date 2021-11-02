package com.example.anadolubankproject.ui.base.interfaces

import io.reactivex.disposables.Disposable

interface IBaseSubscriber {
    fun unSubscribe()
    fun subscribe(disposable: Disposable)
}