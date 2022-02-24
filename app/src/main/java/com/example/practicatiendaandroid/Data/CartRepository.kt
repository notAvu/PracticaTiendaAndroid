package com.example.practicatiendaandroid.Data

import com.example.practicatiendaandroid.Clases.Cart
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Clases.toDomain
import com.example.practicatiendaandroid.Data.DAO.CartDao
import com.example.practicatiendaandroid.Data.DAO.ProductCartDAO
import com.example.practicatiendaandroid.Data.Entities.CartEntity
import com.example.practicatiendaandroid.Data.realtion.CartWithProducts
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao, private val productCartDao: ProductCartDAO) {
    suspend fun getAllCartsFromDatbase(): List<Cart> {
        val response: List<CartEntity> = cartDao.getCartsList()
        return response.map { it.toDomain() }
    }
    suspend fun getAllProductsFromCart():List<Product>{
        val response:List<CartWithProducts> = productCartDao.getProductsInCart()
        return response[0].cartProductList.map { it.toDomain() }
    }

//    suspend fun insertProduct(product: Product) {
//        cartDao.insert(product.toDatbase())
//    }
}