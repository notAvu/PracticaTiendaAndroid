package com.example.practicatiendaandroid.Data.DAO

import androidx.room.*
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity
import com.example.practicatiendaandroid.Data.realtion.CartWithProducts

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    suspend fun getCartsList():List<CartEntity>
    @Transaction
    @Query("SELECT * FROM cart_table")
    suspend fun getProductsInCart():List<CartWithProducts>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(product:ProductEntity)

}
