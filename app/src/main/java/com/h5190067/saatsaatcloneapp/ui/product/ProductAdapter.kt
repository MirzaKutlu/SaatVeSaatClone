package com.h5190067.saatsaatcloneapp.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190067.saatsaatcloneapp.data.model.Product
import com.h5190067.saatsaatcloneapp.databinding.CardViewProductsBinding
import com.h5190067.saatsaatcloneapp.util.GildeUtil
import com.h5190067.saatsaatcloneapp.util.OnItemClickListener

class ProductAdapter(
    val productList: List<Product>,
    var onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardViewProductsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtProductName.text = productList[position].MarkaAdi
                txtPrice.text = productList[position].Fiyat
                GildeUtil.glideImage(
                    imgProduct.context.applicationContext,
                    productList[position].UrunResim!!,
                    imgProduct
                )

                CardViewProduct.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}