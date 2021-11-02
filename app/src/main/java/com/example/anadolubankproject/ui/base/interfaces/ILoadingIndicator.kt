package com.example.anadolubankproject.ui.base.interfaces

interface ILoadingIndicator {
    fun setLoadingIndicator(pair: Pair<Boolean, Boolean>)
    fun setLoadingIndicator(loader: Boolean, rootView: Boolean = false)
}