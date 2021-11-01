package com.example.portableinventory.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.portableinventory.R
import com.example.portableinventory.databinding.FragmentProductDetailsBinding
import com.example.portableinventory.util.ImageUtil
import com.example.portableinventory.util.configureHomeButtonAsBack

class ProductDetailsFragment : Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.configureHomeButtonAsBack(true)
        return DataBindingUtil.inflate<FragmentProductDetailsBinding>(
            inflater,
            R.layout.fragment_product_details,
            container,
            false
        ).also {
            val selectedProduct = args.product
            it.product = selectedProduct
            it.imageView.setImageURI(
                selectedProduct.imgName?.let { filename ->
                    ImageUtil.getUriForFilename(
                        it.root.context,
                        filename
                    )
                }
            )
        }.root
    }
}