package com.example.anadolubankproject.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Post")
data class Post(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0,

    @ColumnInfo(name = "postID")
    var postID: String,

    @ColumnInfo(name = "postTitle")
    var postTitle: String,

    @ColumnInfo(name = "postBody")
    var postBody: String,

    @ColumnInfo(name = "postState")
    var isFav: Boolean
)