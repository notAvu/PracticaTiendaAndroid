package com.example.practicatiendaandroid.Clases

import com.example.practicatiendaandroid.Data.Entities.CartEntity

data class Cart(val id:Int, val status:Boolean)
fun CartEntity.toDomain()=Cart(id, status)
