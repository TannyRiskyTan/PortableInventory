package com.example.portableinventory.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.portableinventory.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_picture_option_bottom_sheet.view.*

class PictureOptionBottomSheetFragment
    : BottomSheetDialogFragment() {

    private lateinit var optClickListener: (View) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_picture_option_bottom_sheet,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.linear_layout_use_camera.setOnClickListener(optClickListener)

        view.linear_layout_use_gallery.setOnClickListener(optClickListener)
    }

    fun setOptClickListener(listener: (View) -> Unit) {
        optClickListener = listener
    }
}