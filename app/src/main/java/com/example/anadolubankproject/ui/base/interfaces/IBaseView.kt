package com.example.anadolubankproject.ui.base.interfaces

import com.example.anadolubankproject.model.BaseThrowable


interface IBaseView {
    fun onError(t: BaseThrowable)
}