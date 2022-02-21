package com.example.practicatiendaandroid.Data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.Entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    suspend fun getProductList():ArrayList<ProductEntity>
//    @Query("SELECT * FROM products_table WHERE price<40")
//    suspend fun getFilterPrice():ArrayList<ProductEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList:List<ProductEntity>){

    }

}