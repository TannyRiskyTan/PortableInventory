package com.example.portableinventory.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.portableinventory.R
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.presentation.adapter.ProductListAdapter
import com.example.portableinventory.presentation.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.fragment_product_list.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    //    private lateinit var navController: NavController
    private val productViewModel: ProductViewModel by activityViewModels()
    private val pagingAdapter = ProductListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        observeData()
        setFABListener(view)
        setOnItemClickListener()
    }

    private fun setOnItemClickListener() {
        pagingAdapter.setOnItemClickListener { product ->
            val bundle = Bundle().apply {
                putParcelable(Product.TAG, product)
            }
            findNavController().navigate(
                R.id.action_productListFragment_to_productDetailsFragment,
                bundle
            )
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setRecyclerView() {
        recycler_view_prod_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pagingAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
        }
    }

    private fun setFABListener(view: View) {
        view.fab.setOnClickListener {
            findNavController().navigate(
                R.id.action_productListFragment_to_addProductFragment
            )
        }
    }
}