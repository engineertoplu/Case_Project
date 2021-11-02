package com.example.anadolubankproject.service

import com.example.anadolubankproject.constants.ApiUrls
import com.example.anadolubankproject.model.dashboard.PostsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DashboradService {

    @GET(ApiUrls.LIST)
    fun getList(): Single<PostsResponse>
}