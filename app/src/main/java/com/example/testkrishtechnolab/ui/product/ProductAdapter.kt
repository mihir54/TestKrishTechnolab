package com.example.testkrishtechnolab.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testkrishtechnolab.data.local.entity.ProductEntity

import com.example.testkrishtechnolab.databinding.ProductItemGridBinding
import com.example.testkrishtechnolab.databinding.ProductItemsBinding

class ProductAdapter(private val layoutManager: GridLayoutManager) : ListAdapter<ProductEntity, RecyclerView.ViewHolder>(Companion) {

    enum class ViewType {
        GRID,
        LIST
    }

    class ProductViewHolder(private val binding : ProductItemsBinding) : RecyclerView.ViewHolder(binding.root){
        constructor(parent: ViewGroup): this(ProductItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        fun bind(product : ProductEntity){
            binding.product = product
            binding.executePendingBindings()
        }
    }

    class ProductGridViewHolder(private val binding2 : ProductItemGridBinding) : RecyclerView.ViewHolder(binding2.root){
        constructor(parent: ViewGroup): this(ProductItemGridBinding.inflate(LayoutInflater.from(parent.context),parent,false))


        fun bind(product : ProductEntity){
            binding2.product = product
            binding2.executePendingBindings()
        }
    }

    companion object: DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean = oldItem.name === newItem.name
        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ViewType.LIST.ordinal -> {
               ProductViewHolder(parent)
            }
            else -> {
                ProductGridViewHolder(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        if (getItemViewType(position)==ViewType.LIST.ordinal){
            (holder as  ProductViewHolder).bind(product)
        }else{
            (holder as ProductGridViewHolder).bind(product)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }
}

/*class ProductAdapter : ListAdapter<ProductEntity, ProductAdapter.ProductViewHolder>(Companion) {

    /*enum class ViewType {
        SMALL,
        DETAILED
    }*/

    class ProductViewHolder(val binding : ProductItemsBinding) : RecyclerView.ViewHolder(binding.root)

   // class ProductGridViewHolder(val binding2 : ProductItemGridBinding) : RecyclerView.ViewHolder(binding2.root)

    companion object: DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean = oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        /*return when (viewType) {
            ViewType.DETAILED.ordinal -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemGridBinding.inflate(layoutInflater)
                ProductGridViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemsBinding.inflate(layoutInflater)
                ProductViewHolder(binding)
            }
        }*/

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemsBinding.inflate(layoutInflater,parent,false)

        return ProductViewHolder(binding)
    }

    /*override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.product = product
        holder.binding.executePendingBindings()
    }*/

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.binding.product = product
        holder.binding.executePendingBindings()



    }

    /*override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount == 1) ViewType.DETAILED.ordinal
        else ViewType.SMALL.ordinal
    }*/


}*/
