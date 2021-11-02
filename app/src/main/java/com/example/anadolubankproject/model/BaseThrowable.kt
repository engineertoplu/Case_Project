package com.example.anadolubankproject.model

import android.content.DialogInterface
import com.example.anadolubankproject.R

open class BaseThrowable : Throwable {

    var showMessage: Boolean = true
        private set
    var stopLoading: Boolean = true
        private set
    var finishScreen: Boolean = false
        private set
    var uiMessage: Any
        get() = if (-1 == field || "" == field) noneEmptyMessage else field
        private set

    var retryListener: ((dialog: DialogInterface, Int) -> Unit)? = null
        private set



    fun setUiMessageForAccess(message: Any){
        uiMessage = message
    }

    protected open var noneEmptyMessage = R.string.error_generic

    constructor(cause: Throwable, finishScreen: Boolean = false, uiMessage: Any = "",
                retryListener: ((dialog: DialogInterface, Int) -> Unit)? = null) : super(cause) {
        this.showMessage = true
        this.stopLoading = true
        this.finishScreen = finishScreen
        this.uiMessage = uiMessage
        this.retryListener = retryListener
    }

    constructor(finishScreen: Boolean = false, uiMessage: Any = "",
                retryListener: ((dialog: DialogInterface, Int) -> Unit)? = null, showMessage:Boolean = true) : super(uiMessage.toString()) {
        this.showMessage = showMessage
        this.stopLoading = true
        this.finishScreen = finishScreen
        this.uiMessage = uiMessage
        this.retryListener = retryListener
    }
}