package com.example.anadolubankproject.ui.base

enum class Status { SUCCESS, ERROR, LOADING }

@Suppress("DataClassPrivateConstructor")
@Deprecated("heyyy")
data class ApiResult<out T> private constructor(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data)
        }

        fun <T> error(): ApiResult<T> {
            return ApiResult(Status.ERROR, null)
        }
    }

    val isSuccess
        get() = status == Status.SUCCESS

    val isError get() = status == Status.ERROR
}