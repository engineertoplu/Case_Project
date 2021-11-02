package com.example.anadolubankproject.ui.base.interfaces

import io.reactivex.disposables.Disposable

interface IBaseDisposable {
    fun clearDispos()
    fun applyDisposable(disposable: Disposable)
}