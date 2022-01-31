package com.example.practicatiendaandroid.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicatiendaandroid.Clases.Product

class ProductListVM : ViewModel() {
    val productSelected=MutableLiveData<Product>()
}