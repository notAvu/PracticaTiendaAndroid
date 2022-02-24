package com.example.practicatiendaandroid.Data.DAO

import androidx.room.*
import com.example.practicatiendaandroid.Data.realtion.CartWithProducts
import com.example.practicatiendaandroid.Data.realtion.ProductCart

@Dao
interface ProductCartDAO {
    @Transaction
    @Query("SELECT * FROM cart_table")
    suspend fun getProductsInCart(): List<CartWithProducts>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCart(productCart: ProductCart)
}