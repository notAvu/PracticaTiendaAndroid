package com.example.practicatiendaandroid.Data.useCases

import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.ProductRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val repository: ProductRepository){
    suspend operator fun invoke():List<Product>{
        return repository.getAllProductsFromDatabase()
    }
}