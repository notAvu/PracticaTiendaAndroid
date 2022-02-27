package com.example.practicatiendaandroid.Data.DAO

import androidx.room.*
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.Entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    suspend fun getProductList():List<ProductEntity>
//    @Query("SELECT * FROM products_table WHERE price<40")
//    suspend fun getFilterPrice():ArrayList<ProductEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList:List<ProductEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product:ProductEntity)
    @Query("DELETE  FROM products_table")
    suspend fun deleteAllProducts()
}