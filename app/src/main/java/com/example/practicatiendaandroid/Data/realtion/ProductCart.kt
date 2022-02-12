package com.example.practicatiendaandroid.Data.realtion

import androidx.room.Entity

@Entity(primaryKeys = ["productId", "cartId"])
data class ProductCart
    (
    val productId: Int,
    val cartId: Int
    )
