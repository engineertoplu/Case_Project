package com.example.anadolubankproject.ui.fragment.favoriates

import com.example.anadolubankproject.AppControl
import com.example.anadolubankproject.model.room.ItemDBModel
import com.example.anadolubankproject.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val appControl: AppControl) : BaseViewModel() {


    val favList = appControl.appDb?.itemDao()?.getAll()

    fun getPostList(): List<ItemDBModel> {
        val list = arrayListOf<ItemDBModel>()
        favList?.forEach {
            list.add(ItemDBModel(it.postID.toInt(), it.postTitle, it.postBody, it.isFav))
        }
        return list
    }
}