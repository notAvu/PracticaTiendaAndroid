package com.example.practicatiendaandroid.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.Data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(private val productRepository: ProductRepository): ViewModel() {
    val productSelected=MutableLiveData<Product>()
    val productsList=MutableLiveData<List<Product>>()
    fun onCreate(){
        viewModelScope.launch{
            var list=productRepository.getAllProductsFromDatabase()
            if(!list.isNullOrEmpty()){
                productsList.postValue(list)
            }
        }
    }
}