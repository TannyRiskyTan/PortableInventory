package com.example.portableinventory.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.portableinventory.R
import com.example.portableinventory.data.datasource.ProductDatabase
import com.example.portableinventory.data.repository.ProductRepositoryImpl
import com.example.portableinventory.databinding.FragmentAddProductBinding
import com.example.portableinventory.presentation.viewmodel.ProductViewModel
import com.example.portableinventory.presentation.viewmodel.AddAndEditProductViewModelFactory
import com.example.portableinventory.util.DateUtil.createDatePicker
import com.example.portableinventory.util.EMPTY_STRING
import com.example.portableinventory.util.ImageUtil.copyImgFromUri
import com.example.portableinventory.util.ImageUtil.createSaveUri
import com.example.portableinventory.util.ImageUtil.takeImageFromCamera
import com.example.portableinventory.util.ImageUtil.takeImageFromGallery
import com.example.portableinventory.util.configureHomeButtonAsBack
import com.example.portableinventory.util.toast
import kotlinx.android.synthetic.main.fragment_add_product.view.*

class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    private lateinit var addAndEditProductViewModel: ProductViewModel
    private val pictureOptBottomSheet = PictureOptionBottomSheetFragment()
    private var imgTempUri: Uri? = null
    private var imgFilename: String = EMPTY_STRING
    private lateinit var cameraLauncher: ActivityResultLauncher<Uri?>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { isSaved ->
            if (isSaved) {
                requireContext().toast(getString(R.string.img_saved_message))
                binding.imageView.setImageURI(imgTempUri)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    intent.data?.let { galleryUri ->
                        createSaveUri()?.let { (absPath, tempUri) ->
                            imgFilename = absPath
                            tempUri?.let {
                                copyImgFromUri(galleryUri, it)
                            }
                        }
                        binding.imageView.setImageURI(galleryUri)
                    }
                }
                requireContext().toast(getString(R.string.img_saved_message))
            }
        }

        addAndEditProductViewModel =
            ViewModelProvider(
                this,
                AddAndEditProductViewModelFactory(
                    ProductRepositoryImpl(ProductDatabase(requireContext()))
                )
            ).get(ProductViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.configureHomeButtonAsBack(true)
        return DataBindingUtil.inflate<FragmentAddProductBinding>(
            inflater,
            R.layout.fragment_add_product,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextProductExpiryDate.setOnClickListener {
            createDatePicker { selectedDate ->
                binding.editTextProductExpiryDate.setText(selectedDate)
            }
        }

        view.image_view_holder.setOnClickListener {
            pictureOptBottomSheet.show(
                childFragmentManager,
                getString(R.string.pic_opt_bottom_sheet_frag)
            )
        }

        pictureOptBottomSheet.setOptClickListener {
            when (it.id) {
                R.id.linear_layout_use_camera -> {
                    if (imgTempUri == null) {
                        createSaveUri()?.let { pair ->
                            imgTempUri = pair.second
                            imgFilename = pair.first
                        }
                    }
                    imgTempUri?.let { uri ->
                        takeImageFromCamera(cameraLauncher, uri)
                    }
                }
                R.id.linear_layout_use_gallery -> {
                    takeImageFromGallery(galleryLauncher)
                }
            }
            pictureOptBottomSheet.dismiss()
        }

        binding.viewmodel = addAndEditProductViewModel

        binding.buttonAddProduct.setOnClickListener {
            addAndEditProductViewModel.apply {
                savingImgName = imgFilename
                saveProduct()
            }
            findNavController().popBackStack()
        }

        binding.buttonIncQty.setOnClickListener {
            binding.textViewQuantity.let { textView ->
                textView.text = (textView.text.toString().toInt() + 1).toString()
            }
        }

        binding.buttonDecQty.setOnClickListener {
            binding.textViewQuantity.let { textView ->
                if (textView.text.toString().toInt() > 0)
                    textView.text = (textView.text.toString().toInt() - 1).toString()
            }
        }
    }

    override fun onDestroy() {
        cameraLauncher.unregister()
        galleryLauncher.unregister()
        super.onDestroy()
    }
}