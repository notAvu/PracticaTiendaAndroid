package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.ProductListAdapter.ProductAdapter
import com.example.practicatiendaandroid.R
import com.example.practicatiendaandroid.databinding.FragmentProductListBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.platform.Hold

private fun iniList(): ArrayList<Product>
{
    val tempList:ArrayList<Product> = ArrayList()
    tempList.add(0, Product(1,"Fantastic Granite Bench",23F, 23F,"Outdoors, Tools & Toys","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU"))
    tempList.add(1, Product(2,"Peluche totoro uwu",12F, 0.56F,"Clothing & Games","https://cdn.shopify.com/s/files/1/0424/3544/4900/products/product-image-1585079422.jpg?v=1623132447"))
    tempList.add(2, Product(3,"Silla gamer",223F, 223F,"Sports","https://pbs.twimg.com/media/FLVCGcuXoAARVgi?format=jpg&name=large"))
    tempList.add(2, Product(3,"Silksong't",42.5F, 23F,"Sports","https://pbs.twimg.com/media/FGN-4ouXwAA5ePY?format=jpg&name=small"))

    return tempList
}

class ProductList : Fragment() {
    private lateinit var productsList: ArrayList<Product>
    private lateinit var navController: NavController
    private val viewModel:ProductListVM by activityViewModels()
    private var auxBinding:FragmentProductListBinding?=null
    private val valBind get()=auxBinding!!
    private val detailsFragment=DetailsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailsFragment.sharedElementEnterTransition= MaterialContainerTransform()
        exitTransition = Hold()
//        arguments?.let {
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        auxBinding = FragmentProductListBinding.inflate(inflater, container, false)
            productsList= iniList()
        return valBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.productSelected.observe(viewLifecycleOwner, this::onProductoSelected)
        navController=findNavController()
        valBind.fragmentProductListRecyclerview.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = ProductAdapter(productsList){onProductoSelected(it, view)}
        }
    }

    private fun onProductoSelected(product: Product,cardView: View) {
//        if(!viewModel.productSelected.equals(productClicked))
//        {
        viewModel.productSelected.postValue(product)
        val toFetailsTransitionName = getString(R.string.product_list__details)
        val extras = FragmentNavigatorExtras(valBind.fragmentProductListRecyclerview[1] to toFetailsTransitionName)
        val directions = ProductListDirections.actionProductListToDetailsFragment(product.id)
        findNavController().navigate(directions, extras)
//        }
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