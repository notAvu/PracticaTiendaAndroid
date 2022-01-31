package com.example.practicatiendaandroid.Clases

import com.example.practicatiendaandroid.Data.Entities.ProductEntity

data class Product(val id:Int, val productName: String, val price:Float, val unitPrice:Float, val category:String, val imageSrc:String)
fun ProductEntity.toDomain()=Product(id, productName, price, unitPrice, category, imageSrc)

