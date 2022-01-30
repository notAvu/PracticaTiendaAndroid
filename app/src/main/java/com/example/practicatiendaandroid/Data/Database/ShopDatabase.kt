package com.example.practicatiendaandroid.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicatiendaandroid.Data.DAO.CartDao
import com.example.practicatiendaandroid.Data.DAO.ProductDao
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity

@Database( entities = [ProductEntity::class, CartEntity::class], version = 1)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun getProductDao():ProductDao
    abstract fun getCartDao(): CartDao

}