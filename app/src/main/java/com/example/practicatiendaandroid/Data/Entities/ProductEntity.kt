package com.example.practicatiendaandroid.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "products_table")
data class ProductEntity (
    @ColumnInfo(name="id") val id: Int=0,
    @ColumnInfo(name="productName") val productName: String,
    @ColumnInfo(name="price") val price: Float,
    @ColumnInfo(name="unitPrice") val unitPrice: Float,
    @ColumnInfo(name="category") val category:String
    )