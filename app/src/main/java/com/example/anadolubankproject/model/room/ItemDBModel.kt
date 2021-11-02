package com.example.anadolubankproject.model.room


class ItemDBModel {
    var itemId: Int? = null
    var title: String? = null
    var body: String? = null
    var isFavorite: Boolean? = null

    constructor() {}
    constructor(id: Int?, title: String, body: String, isFavorite: Boolean) {
        this.itemId = id
        this.title = title
        this.body = body
        this.isFavorite = isFavorite
    }
}