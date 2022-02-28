package com.example.practicatiendaandroid.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.CartRepository
import com.example.practicatiendaandroid.Data.Entities.toDatbase
import com.example.practicatiendaandroid.Data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) :
    ViewModel() {
    val productSelected = MutableLiveData<Product>()
    val vmProdList: MutableLiveData<List<Product>> = MutableLiveData()
    val vmCartItemList:MutableLiveData<MutableList<Product>> = MutableLiveData()
    fun onCreate() {
        if(vmCartItemList.value==null || vmCartItemList.value!!.size<1)
            vmCartItemList.postValue( emptyList<Product>().toMutableList())
    }

    fun buyProduct(product: Product) {
        vmCartItemList.value?.add(product)
//        viewModelScope.launch(Dispatchers.IO) {
//            cartRepository.insertProduct(product)
//        }
    }

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteAllProducts()
            productRepository.insertProducts(defaultProductList().map { it.toDatbase() })
//            cartRepository.insertDefaultCart()
            vmProdList.postValue(productRepository.getAllProductsFromDatabase())
        }
    }

    private fun defaultProductList(): List<Product> {
        val tempList: ArrayList<Product> = ArrayList()
        tempList.add(
            0,
            Product(
                1,
                "Ibuprofeno ",
                23F,
                23F,
                "Medicina",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU"
            )
        )
        tempList.add(
            1,
            Product(
                2,
                "Totoro uwu",
                12F,
                12F,
                "Juguetes",
                "https://cdn.shopify.com/s/files/1/0424/3544/4900/products/product-image-1585079422.jpg?v=1623132447"
            )
        )
        tempList.add(
            2,
            Product(
                3,
                "Silla gamer",
                223F,
                223F,
                "Muebles",
                "https://pbs.twimg.com/media/FLVCGcuXoAARVgi?format=jpg&name=large"
            )
        )
        tempList.add(
            3,
            Product(
                4,
                "Silksong't",
                42.5F,
                23F,
                "Videojuegos",
                "https://pbs.twimg.com/media/FGN-4ouXwAA5ePY?format=jpg&name=small"
            )
        )
        tempList.add(
            4,
            Product(
                3,
                "Dalsy",
                8F,
                8F,
                "Medicina",
                "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/09/23/14746218702234.jpg"
            )
        )
        tempList.add(
            5,
            Product(
                4,
                "Curso aristidevs",
                0F,
                0F,
                "Aristitruco",
                "https://i.ytimg.com/vi/-xRWR_TVa28/hqdefault.jpg?sqp=-oaymwEXCOADEI4CSFryq4qpAwkIARUAAIhCGAE=&rs=AOn4CLBgdsmdXcejgQUliXsZvEqNQob8rA"
            )
        )

        return tempList
    }
}