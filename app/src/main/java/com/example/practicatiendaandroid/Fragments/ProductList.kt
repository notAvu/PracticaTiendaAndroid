package com.example.practicatiendaandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.databinding.FragmentProductListBinding

typealias ProdArrayList=ArrayList<Product>

private fun iniList():ProdArrayList
{
    val tempList:ProdArrayList = ArrayList()
    tempList.add(0, Product(1,"Fantastic Granite Bench",23F, 23F,"Outdoors, Tools & Toys","http://lorempixel.com/g/1366/768/food/"))
    tempList.add(1, Product(2,"Rustic Silk Bag",12F, 0.56F,"Clothing & Games","http://lorempixel.com/g/1366/768/technics/"))
    tempList.add(2, Product(3,"Fantastic Plastic Shirt",42.5F, 23F,"Sports","http://lorempixel.com/g/640/200/city/"))
    return tempList
}

class ProductList : Fragment() {
    private var auxBinding:FragmentProductListBinding?=null
    private val valBind get()=auxBinding!!
    private var productsList:ProdArrayList?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productsList= iniList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auxBinding = FragmentProductListBinding.inflate(inflater, container, false)
        return valBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding=null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductList().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}