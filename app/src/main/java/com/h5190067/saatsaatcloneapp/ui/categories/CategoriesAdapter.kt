package com.h5190067.saatsaatcloneapp.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponseItem
import com.h5190067.saatsaatcloneapp.databinding.CardViewCategoriesBinding
import com.h5190067.saatsaatcloneapp.util.GildeUtil
import com.h5190067.saatsaatcloneapp.util.OnItemClickListener

class CategoriesAdapter(
    var categories: List<CategoriesAndProductsResponseItem>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CardViewCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CardViewCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtCategoryName.text = categories[position].KategoriAdi
                var imageUrl = categories.get(position).kapakResim as String
                GildeUtil.glideImage(imgCategory.context, imageUrl, imgCategory)
                CardViewCategory.setOnClickListener{
                    onItemClickListener.onItemClick(position)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}