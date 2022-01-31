package com.example.practicatiendaandroid.Data

import com.example.practicatiendaandroid.Clases.Cart
import com.example.practicatiendaandroid.Clases.toDomain
import com.example.practicatiendaandroid.Data.DAO.CartDao
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao:CartDao) {
    suspend fun getAllCartsFromDatbase():List<Cart>
    {
        val response:List<CartEntity> = cartDao.getCartsList()
        return response.map { it.toDomain() }
    }
}