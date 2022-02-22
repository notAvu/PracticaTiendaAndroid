package com.example.practicatiendaandroid.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practicatiendaandroid.Clases.Product

@Entity(tableName = "products_table")
data class ProductEntity (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="id") val id: Int=0,
    @ColumnInfo(name="productName") val productName: String,
    @ColumnInfo(name="price") val price: Float,
    @ColumnInfo(name="unitPrice") val unitPrice: Float,
    @ColumnInfo(name="category") val category:String,
    @ColumnInfo(name="imageSrc") val imageSrc:String
    )
fun Product.toDatbase()= ProductEntity(productName = productName, price=price, unitPrice= unitPrice, category=category, imageSrc=imageSrc)