package com.example.practicatiendaandroid.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicatiendaandroid.Data.DAO.CartDao
import com.example.practicatiendaandroid.Data.DAO.ProductDao
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database( entities = [ProductEntity::class, CartEntity::class], version = 1)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun getProductDao():ProductDao
    abstract fun getCartDao(): CartDao
     companion object {
         @Volatile
         private var INSTANCE: ShopDatabase?=null
         @InternalCoroutinesApi
         fun getInstance(context: Context):ShopDatabase {
             synchronized(this) {
                 return INSTANCE ?: Room.databaseBuilder(context.applicationContext,ShopDatabase::class.java,"shop_db").build().also{INSTANCE=it}
             }
             TODO("Solucionar synchronized")
         }
     }
}