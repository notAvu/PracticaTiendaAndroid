package com.example.practicatiendaandroid.Data.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.practicatiendaandroid.Data.Entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    suspend fun getProductList():ArrayList<ProductEntity>

}