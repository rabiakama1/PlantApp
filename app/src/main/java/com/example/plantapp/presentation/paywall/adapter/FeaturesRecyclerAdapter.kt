package com.example.plantapp.presentation.paywall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.databinding.ItemPaywallFeatureBinding
import com.example.plantapp.presentation.paywall.model.FeaturesModel

class FeaturesRecyclerAdapter(private var features: List<FeaturesModel>) : RecyclerView.Adapter<FeaturesRecyclerAdapter.FeaturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val binding =
            ItemPaywallFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeaturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        val question = features[position]
        holder.bindItem(question)
    }

    fun defaultSetNewItemList(newItemList: List<FeaturesModel>) {
        this.features = ArrayList(newItemList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = features.size

    inner class FeaturesViewHolder(private val binding: ItemPaywallFeatureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(model: FeaturesModel) {
            binding.itemDescription.text = model.description
            binding.itemTitle.text = model.type
            binding.itemIcon.setImageResource(model.icon!!)
        }
    }
}