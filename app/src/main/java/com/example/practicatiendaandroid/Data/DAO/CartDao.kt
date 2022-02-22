package com.example.practicatiendaandroid.Data.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.practicatiendaandroid.Data.Entities.CartEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    suspend fun getCartsList():List<CartEntity>

}
