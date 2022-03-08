package com.example.practicatiendaandroid.ui.ViewModels

import android.os.Build
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
    val vmCartItemList: MutableLiveData<MutableList<Product>> = MutableLiveData()
    fun onCreate() {
        if (vmCartItemList.value == null || vmCartItemList.value!!.size < 1)
            vmCartItemList.postValue(emptyList<Product>().toMutableList())
    }

    fun buyProduct(product: Product) {
        if (!vmCartItemList.value!!.contains(product)) {
            vmCartItemList.value!!.add(product)
        }
    }

    fun removeProductFromCart(product: Product) {
        vmCartItemList.value!!.remove(product)
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
                "Penicilina",
                12F,
                12F,
                "Dulces",
                "https://enfermeriabuenosaires.com/wp-content/uploads/2019/02/penicilinasodica5-600x600-600x440.jpg"
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
                "Colgáte",
                42.5F,
                23F,
                "Higiene",
                "https://m.media-amazon.com/images/I/81xv8wGJ4yL._AC_SY355_.jpg"
            )
        )
        tempList.add(
            4,
            Product(
                6,
                "Dalsy",
                8F,
                8F,
                "Medicina",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.elcorreo.com%2Fbizkaia%2Fsociedad%2Fsalud%2F201609%2F19%2Fefectos-secundarios-colorante-dalsy-20160919190622.html&psig=AOvVaw0soIuWFPEcEV1HKJl_ReIs&ust=1646846763272000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCKiq6KyEt_YCFQAAAAAdAAAAABAD"
            )
        )
        tempList.add(
            5,
            Product(
                5,
                "Cristalmina",
                10F,
                10F,
                "AAAAAAAAA",
                "https://www.farmaciasoler.com/img/cache/400x400_cristalmina-10-mg-ml-solucion-para-pulverizacion-cutanea-1-frasco-de-25-ml-0.jpg"
            )
        )

        tempList.add(
            6,
            Product(
                7,
                "Estus",
                10F,
                10F,
                "Via oral",
                "https://static.wikia.nocookie.net/dark-souls/images/5/5f/Frasco_de_estus_%28DSIII%29.png/revision/latest?cb=20170713011822&path-prefix=es"
            )
        )
        return tempList
    }
}