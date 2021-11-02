package com.example.anadolubankproject.ui.base

import com.example.anadolubankproject.model.BaseThrowable

@Suppress("DataClassPrivateConstructor")
data class State private constructor(val status: Status, val error: BaseThrowable?, val loadingIndicator: Pair<Boolean, Boolean>?){
    companion object {
        fun success(loadingIndicator: Pair<Boolean, Boolean>): State {
            return State(Status.SUCCESS, null, loadingIndicator)
        }

        fun error(error: BaseThrowable): State {
            return State(Status.ERROR, error, null)
        }

        fun loading(loadingIndicator: Pair<Boolean, Boolean>?): State {
            return State(Status.LOADING, null, loadingIndicator)
        }
    }

}