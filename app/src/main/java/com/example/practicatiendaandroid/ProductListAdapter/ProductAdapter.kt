package com.example.practicatiendaandroid.ProductListAdapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicatiendaandroid.Clases.Product
import com.example.practicatiendaandroid.R

class ProductAdapter (private val dataSet: MutableList<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>()
{
    class ViewHolder(view:View): RecyclerView.ViewHolder(view)
    {
        val productName:TextView = view.findViewById(R.id.list_element_product_product_name)
        val productImage:ImageView = view.findViewById(R.id.list_element_product_product_image)
        val productPrice:TextView = view.findViewById(R.id.list_element_product_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text=dataSet[position].productName
    }

    override fun getItemCount()=dataSet.size
}