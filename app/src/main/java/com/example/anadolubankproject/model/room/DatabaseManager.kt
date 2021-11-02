package com.example.anadolubankproject.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.anadolubankproject.AppControl
import com.example.anadolubankproject.model.room.dao.ItemDao
import com.example.anadolubankproject.model.room.entity.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {


    abstract fun itemDao(): ItemDao

    companion object {
        var INSTANCE: DatabaseManager? = null

        fun getDatabaseManager(context: Context): DatabaseManager? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseManager::class.java,
                    "post-database"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}