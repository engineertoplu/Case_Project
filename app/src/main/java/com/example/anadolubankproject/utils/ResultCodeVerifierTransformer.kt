package com.example.anadolubankproject.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.anadolubankproject.model.BaseThrowable
import com.example.anadolubankproject.ui.base.BaseApiResponse
import com.example.anadolubankproject.ui.base.State
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers

class ResultCodeVerifierTransformer<T>(private val state: MutableLiveData<State>,
                                       private val isResultNullable: Boolean = false,
                                       private val throwable: BaseThrowable = BaseThrowable()) : SingleTransformer<BaseApiResponse<T>, T> {
    override fun apply(tSingle: Single<BaseApiResponse<T>>): Single<T> {
        return (tSingle
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap { response ->
                    if (response.isSuccessful) {
                        if (response.result != null) {
                            Single.just(response.result)
                        } else if (!isResultNullable)
                            Single.error(throwable)
                        else
                            Single.just(true as T)
                    } else {
                        Single.error(throwable.apply {
                                setUiMessageForAccess(response.responseMessage ?: "")
                            })
                    }
                }
                .doOnSubscribe {
                    Log.d("ResultCode", "doOnSubscribe")
                    state.postValue(State.loading(Pair(true, true)))
                }
                .doAfterSuccess {
                    Log.d("ResultCode", "doAfterSuccess")
                    state.postValue(State.success(Pair(false, true)))
                })
    }
}