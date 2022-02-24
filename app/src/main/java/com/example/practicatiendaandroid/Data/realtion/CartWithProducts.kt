package com.example.practicatiendaandroid.Data.realtion

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity


data class CartWithProducts(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "productId",
        associateBy = Junction(ProductCart::class)
    )
    val cartProductList:List<ProductEntity>
)