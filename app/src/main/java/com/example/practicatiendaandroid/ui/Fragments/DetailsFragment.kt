package com.example.practicatiendaandroid.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practicatiendaandroid.databinding.MaterialDetailsCardBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private val viewModel: ProductListVM by activityViewModels()
    private var auxBinding: MaterialDetailsCardBinding?=null
    private val valBind get()=auxBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition=MaterialContainerTransform()
    //        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auxBinding = MaterialDetailsCardBinding.inflate(inflater, container, false)
//        productsList= iniList()
        return valBind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selected=viewModel.productSelected.value
        valBind.apply {
            materialDetailsCardProductName.text= selected?.productName ?: ""
            materialDetailsCardCategory.text= selected?.category?:""
            materialDetailsCardProductPrice.text= (selected?.price ?: 0).toString()
//            materialDetailsCardDescriptionText.text=selected?.category ?: ""
            Picasso.get().load(selected?.imageSrc).into(materialDetailsCardProductImage)
        }
    }
//    companion object {
//    }
}