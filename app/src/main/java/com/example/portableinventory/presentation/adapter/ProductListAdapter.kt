package com.example.portableinventory.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.portableinventory.databinding.ProductViewBinding
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.util.ImageUtil

class ProductListAdapter :
    PagingDataAdapter<Product, RecyclerView.ViewHolder>(ProductComparator) {

    private var onItemClickListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            ProductViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder)
            getItem(position)?.let { product -> holder.bind(product) }
    }

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductViewHolder(private val binding: ProductViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product

            product.imgName?.let { filename ->
                binding.imageView.setImageURI(
                    ImageUtil.getUriForFilename(
                        context = binding.root.context,
                        filename = filename
                    )
                )
            }
            binding.root.setOnClickListener { onItemClickListener?.let { it(product) } }
        }
    }

    private object ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}