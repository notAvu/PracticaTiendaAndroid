package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import com.squareup.picasso.Picasso


class ProductList : Fragment() {
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
//        filterButton = valBind.fragmentProductListFab
//        filterButton.setOnClickListener {
//            showFilterDialog()
//        }
        return valBind.root
    }

    private fun OnProdListLoaded(productList:List<Product>) {
        valBind.fragmentProductListRecyclerview.adapter = viewModel.vmProdList.value?.let {
            ProductAdapter(
                R.layout.material_card_product,
                it
            ) { onProductoSelected(it) }
        }
        viewModel.vmProdList.value?.forEach {
            val category = it.category
            if (!categoriesList.contains(category))
                categoriesList.add(category)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.vmProdList.observe(viewLifecycleOwner, this::OnProdListLoaded)
        viewModel.loadProducts()
        valBind.fragmentProductListRecyclerview.apply {
            layoutManager = GridLayoutManager(view.context, 2)
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
    private fun showDetailsDialog(productClicked: Product){
        val detailsDialog = layoutInflater.inflate(R.layout.material_details_card, null)
        val prodImage:ImageView=detailsDialog.findViewById(R.id.material_details_card__product_image)
        val prodName:TextView=detailsDialog.findViewById(R.id.material_details_card__product_name)
        val prodCategory:TextView=detailsDialog.findViewById(R.id.material_details_card__category)
        val prodPrice:TextView=detailsDialog.findViewById(R.id.material_details_card__product_price)
        val buyButton: Button =detailsDialog.findViewById(R.id.material_details_card__buy_product)
        prodName.text=productClicked.productName
        prodPrice.text= productClicked.price.toString()+"€"
        prodCategory.text=productClicked.category
        Picasso.get().load(productClicked.imageSrc).into(prodImage)
        buyButton.setOnClickListener {
            viewModel.buyProduct(productClicked)
        }
        MaterialAlertDialogBuilder(requireContext())
            .setView(detailsDialog)
            .setCancelable(true)
            .show()
    }

    private fun onProductoSelected(productClicked: Product) {
        showDetailsDialog(productClicked)
//        viewModel.productSelected.postValue(productClicked)
//        navController.navigate(R.id.action_productList_to_detailsFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding = null
    }
}