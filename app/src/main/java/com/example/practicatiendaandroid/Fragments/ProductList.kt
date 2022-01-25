package com.example.practicatiendaandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicatiendaandroid.Clases.Producto
import com.example.practicatiendaandroid.databinding.FragmentProductListBinding

private fun iniList():ArrayList<Producto>
{
    val tempList:ArrayList<Producto> = ArrayList()
    tempList.add(0, Producto(1,"AS",23F, 23F,"Planta",""))
    tempList.add(1, Producto(2,"SD",12F, 0.56F,"Planta",""))
    tempList.add(2, Producto(3,"DF",23F, 23F,"Planta",""))
    return tempList
}

class ProductList : Fragment() {
    private var bind:FragmentProductListBinding?=null
    private val valBind get()=bind!!
    private var productsList:ArrayList<Producto>?=null

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
//        var inflateur = inflater.inflate(R.layout.fragment_product_list, container, false)
        bind = FragmentProductListBinding.inflate(inflater, container, false)
        return valBind.root
//        inflateur=
//        return inflateur
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind=null
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