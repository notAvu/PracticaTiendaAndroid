package com.example.practicatiendaandroid.Data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    suspend fun getCartsList():List<CartEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product:ProductEntity)

}
