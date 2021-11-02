package com.example.anadolubankproject.model.room.dao

import android.content.ClipData
import androidx.room.*
import com.example.anadolubankproject.model.room.ItemDBModel
import com.example.anadolubankproject.model.room.entity.Post

@Dao
interface ItemDao {
    @Query("SELECT * FROM Post")
    fun getAll(): List<Post>

    @Query("SELECT * FROM Post where postID LIKE  :id")
    fun findById(id: String): Post?

    @Query("SELECT * FROM Post where postID LIKE  :id")
    fun deleteById(id: String): Post

    @Insert
    fun insert(item: Post)

    @Query("Update Post set postState = :isFav where postID LIKE  :id")
    fun updateByPost(isFav: Boolean, id: String)

    @Transaction
    fun insertOrUpdate(item: ItemDBModel) {
        if (findById(item.itemId.toString()) == null) {
            insert(
                Post(
                    postID = item.itemId.toString(),
                    postTitle = item.title!!,
                    postBody = item.body!!,
                    isFav = true
                )
            )
        } else{
            insert(
                Post(
                    postID = item.itemId.toString(),
                    postTitle = item.title!!,
                    postBody = item.body!!,
                    isFav = item.isFavorite?:false
                )
            )
        }
    }
}