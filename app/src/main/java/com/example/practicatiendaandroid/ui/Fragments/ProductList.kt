package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    private var categoriesList = ArrayList<String>()
    lateinit var adapter: ProductAdapter
    private lateinit var filterButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem=menu.findItem(R.id.app_bar_menu__search_bar)
            val searchView=searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    val filteredProductlist: List<Product>? =
                        viewModel.vmProdList.value?.filter { product -> product.productName.contains("tot")
                    }
                    if (filteredProductlist != null) {
                        updateListData(filteredProductlist)
                    }
                    return true
                }
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auxBinding = FragmentProductListBinding.inflate(inflater, container, false)
        filterButton = valBind.fragmentProductListFab
        filterButton.setOnClickListener {
            showFilterDialog()
        }
        return valBind.root
    }

    private fun OnProdListLoaded(productList:List<Product>) {
        updateListData(productList)
        productList.forEach {
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
            .setPositiveButton("Filtrar") { _, _ ->
                val criteria: String = orderCriteriaSpinner.selectedItem.toString()
                val category: String = categoriesSpinner.selectedItem.toString()
                filterList(criteria, category)
            }
            .show()
    }

    private fun filterList(criteria: String, selectedCategory: String) {
        val filteredProductlist: List<Product> = if(criteria == "Precio") {
            viewModel.vmProdList.value!!.filter { product -> product.category==selectedCategory
            }.sortedBy { it.price }
        } else {
            viewModel.vmProdList.value!!.filter { product -> product.category==selectedCategory
            }.sortedBy { it.productName }
        }
        updateListData(filteredProductlist)
    }

    private fun showDetailsDialog(productClicked: Product){
        val detailsDialog = layoutInflater.inflate(R.layout.material_details_card, null)
        val prodImage:ImageView=detailsDialog.findViewById(R.id.material_details_card__product_image)
        val prodName:TextView=detailsDialog.findViewById(R.id.material_details_card__product_name)
        val prodCategory:TextView=detailsDialog.findViewById(R.id.material_details_card__category)
        val prodPrice:TextView=detailsDialog.findViewById(R.id.material_details_card__product_price)
        val buyButton: Button =detailsDialog.findViewById(R.id.material_details_card__buy_product)
        val prodUnitPrice:TextView=detailsDialog.findViewById(R.id.material_details_card__unit_price)
        prodName.text=productClicked.productName
        prodUnitPrice.text=productClicked.unitPrice.toString()+"€/unidad"
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

    private fun updateListData(prodList:List<Product>){
        adapter= ProductAdapter(R.layout.material_card_product,prodList){ onProductoSelected(it)}
        valBind.fragmentProductListRecyclerview.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding = null
    }
}