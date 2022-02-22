package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.ProductListAdapter.ProductAdapter
import com.example.practicatiendaandroid.R
import com.example.practicatiendaandroid.databinding.FragmentProductListBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

private fun iniList(): ArrayList<Product> {
    val tempList: ArrayList<Product> = ArrayList()
//    tempList.add(0, Product(1,"Fantastic Granite Bench",23F, 23F,"Outdoors, Tools & Toys","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPcis2nSFZAO2nG4enJj0xxHBgVkxTuiXukg&usqp=CAU"))
//    tempList.add(1, Product(2,"Totoro uwu",12F, 0.56F,"Clothing & Games","https://cdn.shopify.com/s/files/1/0424/3544/4900/products/product-image-1585079422.jpg?v=1623132447"))
//    tempList.add(2, Product(3,"Silla gamer",223F, 223F,"Sports","https://pbs.twimg.com/media/FLVCGcuXoAARVgi?format=jpg&name=large"))
//    tempList.add(2, Product(3,"Silksong't",42.5F, 23F,"Sports","https://pbs.twimg.com/media/FGN-4ouXwAA5ePY?format=jpg&name=small"))
    return tempList
}

class ProductList : Fragment() {
    private lateinit var productsList: List<Product>
    private lateinit var navController: NavController
    private val viewModel: ProductListVM by activityViewModels()
    private var auxBinding: FragmentProductListBinding? = null
    private val valBind get() = auxBinding!!
    private val detailsFragment = DetailsFragment()
    private var categoriesList = ArrayList<String>()
    private lateinit var filterButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auxBinding = FragmentProductListBinding.inflate(inflater, container, false)
        productsList = iniList()
        productsList = viewModel.vmProdList.value!!
        productsList.forEach {
            val category = it.category
            if (!categoriesList.contains(category))
                categoriesList.add(category)
        }
        filterButton = valBind.fragmentProductListFab
        filterButton.setOnClickListener {
            showFilterDialog()

        }
        return valBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        valBind.fragmentProductListRecyclerview.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = ProductAdapter(
                R.layout.material_card_product,
                productsList
            ) { onProductoSelected(it) }
        }
    }

    private fun showFilterDialog() {
        val filterDialog = layoutInflater.inflate(R.layout.filter_dialog_layout, null)
        //        viewModel.productSelected.observe(viewLifecycleOwner, this::onProductoSelected)
        val orderCriteriaSpinner =
            filterDialog.findViewById<Spinner>(R.id.filter_dialog_layout__order_criteria_spinner)
        val categoriesSpinner =
            filterDialog.findViewById<Spinner>(R.id.filter_dialog_layout__categories_spinner)
        val orderByAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            mutableListOf("Precio", "Nombre")
        )
        val filterAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categoriesList
        )
        categoriesSpinner.adapter = filterAdapter
        orderCriteriaSpinner.adapter = orderByAdapter
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Filtrar")
            .setView(filterDialog)
            .setCancelable(true)
            .setPositiveButton("Filtrar") { dialogInterface, which ->
                val category: String = orderCriteriaSpinner.selectedItem.toString()
                val criteria: String = categoriesSpinner.selectedItem.toString()
            }
            .show()
    }

    private fun onProductoSelected(productClicked: Product) {
        viewModel.productSelected.postValue(productClicked)
//        val navExtras= FragmentNavigatorExtras(view!! to "shared_element_container")
//        navController.navigate(R.id.action_productList_to_detailsFragment, null, null, navExtras)
        navController.navigate(R.id.action_productList_to_detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding = null
    }
}