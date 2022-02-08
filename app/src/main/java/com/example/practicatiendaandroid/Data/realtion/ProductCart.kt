package com.example.practicatiendaandroid.Data.realtion

import androidx.room.Embedded
import androidx.room.Relation
import com.example.practicatiendaandroid.Clases.Product

data class ProductCart
    (
    @Embedded val product: Product,
//    @Relation
//        (
//        parentColumn = "idProduct"
//        entityColumn="id"
//        )
//        val cart:Cart
            )