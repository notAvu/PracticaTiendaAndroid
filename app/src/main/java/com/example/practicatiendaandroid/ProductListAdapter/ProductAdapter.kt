package com.example.practicatiendaandroid.ProductListAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.R
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val viewModel: ProductListVM,
    private val layout:Int,
    private val dataSet: List<Product>,
    private val listener: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val deleteBtn: Button = view.findViewById(R.id.list_element_product_remove_product)
        val productName: TextView = view.findViewById(R.id.list_element_product_product_name)
        val productImage: ImageView = view.findViewById(R.id.list_element_product_product_image)
        val productPrice: TextView = view.findViewById(R.id.list_element_product_product_price)
//        fun removeItem(product:Product, viewModel: ProductListVM){
//            deleteBtn.setOnClickListener{
//                viewModel.removeProductFromCart(product)
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            productName.text = dataSet[position].productName
            productPrice.text = dataSet[position].price.toString()+"€"
            itemView.setOnClickListener { listener(dataSet[position])
//            removeItem(dataSet[position], viewModel)
            }
        }
        Picasso.get().load(dataSet[position].imageSrc).into(holder.productImage)
    }

    override fun getItemCount() = dataSet.size
}