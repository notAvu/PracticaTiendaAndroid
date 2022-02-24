package com.example.practicatiendaandroid.Data.DAO

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.practicatiendaandroid.Data.realtion.CartWithProducts

@Dao
interface ProductCartDAO {
    @Transaction
    @Query("SELECT * FROM cart_table")
    suspend fun getProductsInCart(): List<CartWithProducts>
}