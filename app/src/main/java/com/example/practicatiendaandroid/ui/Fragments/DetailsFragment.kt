package com.example.practicatiendaandroid.ui.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practicatiendaandroid.R
import com.example.practicatiendaandroid.databinding.FragmentDetailsBinding
import com.example.practicatiendaandroid.ui.ViewModels.ProductListVM
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private val viewModel: ProductListVM by activityViewModels()
    private var auxBinding: FragmentDetailsBinding?=null
    private val valBind get()=auxBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_container
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
            scrimColor = Color.TRANSPARENT
//            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
//        sharedElementEnterTransition=MaterialContainerTransform()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auxBinding = FragmentDetailsBinding.inflate(inflater, container, false)
//        productsList= iniList()
        return valBind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selected=viewModel.productSelected.value
        valBind.apply {
            materialDetailsCardDescriptionText.text=selected?.productName
//            fragmentDetailsTextViewProductName.text= selected?.productName ?: ""
//            fragmentDetailsTextViewProductUnitPrice.text= (selected?.unitPrice?:0).toString()
//            fragmentDetailsTextViewProductPrice.text= (selected?.price ?: 0).toString()
//            fragmentDetailsTextViewUnits.text=selected?.category ?: ""
//            Picasso.get().load(selected?.imageSrc).into(fragmentDetailsImageViewProductImage)
        }
    }
//    companion object {
//    }
}