package com.example.anadolubankproject.ui.base

class BaseApiResponse<out T> {
    companion object {
        private const val API_RESPONSE_CODE_SUCCESS = 200
        private const val API_RESPONSE_CODE_UNAUTHORIZED = 4001
        private const val API_RESPONSE_CODE_SUGGESTION_NOT_FOUND = 1002
        private const val API_RESPONSE_CODE_ROOM_NOT_FOUND = 1003
        private const val API_RESPONSE_CODE_BANNER_NOT_FOUND = 2001
        private const val API_RESPONSE_CODE_FAILEDLOGIN = 5001
    }

    val responseCode: Int = 0
    val responseMessage: String? = null
    val result: T? = null

    val isSuccessful: Boolean
        get() = this.responseCode == API_RESPONSE_CODE_SUCCESS
    val isUnAuthorized: Boolean
        get() = this.responseCode == API_RESPONSE_CODE_UNAUTHORIZED

    val isNotFound: Boolean
        get() = this.responseCode == API_RESPONSE_CODE_SUGGESTION_NOT_FOUND || this.responseCode == API_RESPONSE_CODE_ROOM_NOT_FOUND

    val isNotFoundBanner: Boolean
        get() = this.responseCode == API_RESPONSE_CODE_BANNER_NOT_FOUND

    val isFailedLogin: Boolean
        get() = this.responseCode == API_RESPONSE_CODE_FAILEDLOGIN
}