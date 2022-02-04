package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.ProductListAdapter.ProductAdapter
import com.example.practicatiendaandroid.databinding.FragmentProductListBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM

private fun iniList(): ArrayList<Product>
{
    val tempList:ArrayList<Product> = ArrayList()
    tempList.add(0, Product(1,"Fantastic Granite Bench",23F, 23F,"Outdoors, Tools & Toys","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU"))
    tempList.add(1, Product(2,"Rustic Silk Bag",12F, 0.56F,"Clothing & Games","http://lorempixel.com/g/1366/768/technics/"))
    tempList.add(2, Product(3,"Fantastic Plastic Shirt",42.5F, 23F,"Sports","http://lorempixel.com/g/640/200/city/"))
    tempList.add(2, Product(3,"Fantastic Plastic Shirt",42.5F, 23F,"Sports","http://lorempixel.com/g/640/200/city/"))
    tempList.add(2, Product(3,"Fantastic Plastic Shirt",42.5F, 23F,"Sports","http://lorempixel.com/g/640/200/city/"))
    tempList.add(2, Product(3,"Fantastic Plastic Shirt",42.5F, 23F,"Sports","http://lorempixel.com/g/640/200/city/"))

    return tempList
}

class ProductList : Fragment() {
    private lateinit var productsList: ArrayList<Product>
    private val viewModel:ProductListVM by activityViewModels()
    private var auxBinding:FragmentProductListBinding?=null
    private val valBind get()=auxBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auxBinding = FragmentProductListBinding.inflate(inflater, container, false)
            productsList= iniList()
        return valBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productSelected.observe(viewLifecycleOwner, this::onProductoSelected)
        valBind.fragmentProductListRecyclerview.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ProductAdapter(productsList){onProductoSelected(it)}
        }
    }

    private fun onProductoSelected(productClicked: Product) {
        viewModel.productSelected.postValue(productClicked)
        TODO("Ver navComponent (pasar solo el id cuando pases objetos)")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding=null
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ProductList.
//         */
//        //Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProductList().apply {
//                arguments = Bundle().apply {
////                    putString(ARG_PARAM1, param1)
////                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}