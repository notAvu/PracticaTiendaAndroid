package com.example.practicatiendaandroid.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.DAO.CartDao
import com.example.practicatiendaandroid.Data.DAO.ProductDao
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.Entities.ProductEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.internal.synchronized
import javax.inject.Inject

@Database( entities = [ProductEntity::class, CartEntity::class], version = 1)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun getProductDao():ProductDao
    abstract fun getCartDao(): CartDao
     companion object {
         @Volatile
         private var INSTANCE: ShopDatabase?=null
//         @InternalCoroutinesApi
//         fun getInstance(context: Context):ShopDatabase {
//             synchronized(this) {
//                 return INSTANCE ?: Room.databaseBuilder(context.applicationContext,ShopDatabase::class.java,"shop_db").addCallback(CALLBACK).build().also{INSTANCE=it}
//             }
//         }
//             private val CALLBACK = object : RoomDatabase.Callback() {
//                 override fun onCreate(db: SupportSQLiteDatabase) {
//                     super.onCreate(db)
//
//                     db.execSQL("INSERT INTO products_table VALUES ('Paracetamol',12,12,'medicamentos','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU'),('Silla gamer',150,150,'Muebles','https://pbs.twimg.com/media/FLVCGcuXoAARVgi?format=jpg&name=large'),('Totoro',20,20,'terapia','https://cdn.shopify.com/s/files/1/0424/3544/4900/products/product-image-1585079422.jpg?v=1623132447')")
//                 }
//     }

}
}
//val tempList:ArrayList<Product> = ArrayList()
//tempList.add(0, Product(1,"Fantastic Granite Bench",23F, 23F,"Outdoors, Tools & Toys","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU"))
//tempList.add(1, Product(2,"Totoro uwu",12F, 0.56F,"Clothing & Games","https://cdn.shopify.com/s/files/1/0424/3544/4900/products/product-image-1585079422.jpg?v=1623132447"))
//tempList.add(2, Product(3,"Silla gamer",223F, 223F,"Sports","https://pbs.twimg.com/media/FLVCGcuXoAARVgi?format=jpg&name=large"))
//tempList.add(2, Product(3,"Silksong't",42.5F, 23F,"Sports","https://pbs.twimg.com/media/FGN-4ouXwAA5ePY?format=jpg&name=small"))
//return tempList