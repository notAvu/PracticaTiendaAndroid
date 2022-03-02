package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.map
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.ProductListAdapter.ProductAdapter
import com.example.practicatiendaandroid.R
import com.example.practicatiendaandroid.databinding.FragmentCartBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso

class CartFragment : Fragment() {
    private lateinit var navController: NavController
    private val viewModel: ProductListVM by activityViewModels()
    private var auxBinding: FragmentCartBinding?=null
    private val valBind get()=auxBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auxBinding = FragmentCartBinding.inflate(inflater, container, false)
        return valBind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=findNavController()
        viewModel.loadProducts()
        valBind.cartFragmentProductRecyclerView.apply {
            layoutManager=LinearLayoutManager(view.context)
            adapter= viewModel.vmCartItemList.value?.let {
                ProductAdapter(viewModel, R.layout.material_card_cart_item,
                    it
                ){onProductoSelected(it)}
            }
        }
        var precioTotal=0F
        viewModel.vmCartItemList.value?.map { precioTotal+=it.price }
        valBind.cartFragmentPriceText.text= "Total: $precioTotal"
    }

    private fun onProductoSelected(productClicked: Product) {
        showDetailsDialog(productClicked)
    }
    private fun showDetailsDialog(productClicked: Product){
        val detailsDialog = layoutInflater.inflate(R.layout.material_details_card, null)
        val prodImage: ImageView =detailsDialog.findViewById(R.id.material_details_card__product_image)
        val prodName: TextView =detailsDialog.findViewById(R.id.material_details_card__product_name)
        val prodCategory: TextView =detailsDialog.findViewById(R.id.material_details_card__category)
        val prodPrice: TextView =detailsDialog.findViewById(R.id.material_details_card__product_price)
        val prodUnitPrice: TextView =detailsDialog.findViewById(R.id.material_details_card__unit_price)
        prodName.text=productClicked.productName
        prodUnitPrice.text=productClicked.unitPrice.toString()+"€/unidad"
        prodPrice.text= productClicked.price.toString()+"€"
        prodCategory.text=productClicked.category
        Picasso.get().load(productClicked.imageSrc).into(prodImage)
        MaterialAlertDialogBuilder(requireContext())
            .setView(detailsDialog)
            .setCancelable(true)
            .show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        auxBinding=null
    }
}