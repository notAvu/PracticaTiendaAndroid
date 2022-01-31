package com.example.practicatiendaandroid.Data

import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Clases.toDomain
import com.example.practicatiendaandroid.Data.DAO.ProductDao
import com.example.practicatiendaandroid.Data.Entities.ProductEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao:ProductDao) {

    suspend fun getAllProductsFromDatabase():List<Product>
    {
        val response: List<ProductEntity> = productDao.getProductList()
        return response.map { it.toDomain() }
    }
}