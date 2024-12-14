package com.example.plantapp.presentation.home.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.data.dto.remote.dto.category.CategoryItem
import com.example.plantapp.databinding.ItemHomeCategoryBinding

class PlantCategoriesRecyclerAdapter(private val categories: List<CategoryItem>) : RecyclerView.Adapter<PlantCategoriesRecyclerAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val question = categories[position]
        holder.bindItem(question)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(c: CategoryItem) {
            Glide.with(binding.imgCategory.context)
                .load(c.image.url)
                .into(binding.imgCategory)
            binding.txtCategory.text = c.title
        }
    }
}