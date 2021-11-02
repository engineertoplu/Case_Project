package com.example.anadolubankproject.model.dashboard

import java.io.Serializable

data class PostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Serializable