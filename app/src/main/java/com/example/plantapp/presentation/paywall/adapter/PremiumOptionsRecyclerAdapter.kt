package com.example.plantapp.presentation.paywall.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.R
import com.example.plantapp.databinding.ItemPaywallPremiumOptionBinding
import com.example.plantapp.presentation.paywall.model.FeaturesModel
import com.example.plantapp.presentation.paywall.model.PremiumTypeModel

class PremiumOptionsRecyclerAdapter(
    private var options: List<PremiumTypeModel>,
    private var onChecked: (PremiumTypeModel) -> Unit
) : RecyclerView.Adapter<PremiumOptionsRecyclerAdapter.PremiumOptionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumOptionsViewHolder {
        val binding = ItemPaywallPremiumOptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PremiumOptionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PremiumOptionsViewHolder, position: Int) {
        val category = options[position]
        holder.bindItem(category)
    }

    override fun getItemCount(): Int = options.size

    fun defaultSetNewItemList(newItemList: List<PremiumTypeModel>) {
        this.options = ArrayList(newItemList)
        notifyDataSetChanged()
    }

    inner class PremiumOptionsViewHolder(private val binding: ItemPaywallPremiumOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(model: PremiumTypeModel) {
            binding.txtDateOption.text = model.date
            binding.txtDescription.text = model.description
            binding.radioIcon.isChecked = model.isChecked ?: false
            if (model.discount.isNullOrEmpty().not()) {
                binding.txtDiscount.text = model.discount
            }
            if (model.isChecked == true) {
                binding.cardBorder.let {
                    it.background = ContextCompat.getDrawable(it.context, R.drawable.button_border)
                }
            } else {
                binding.cardBorder.setBackgroundColor(Color.TRANSPARENT)
            }
            binding.cardBorder.setOnClickListener {
                model.isChecked = !model.isChecked!!
                if (model.isChecked == true) {
                    for (i in options) {
                        if (i != model) {
                            i.isChecked = false
                        }
                    }
                }
                onChecked(model)
                notifyDataSetChanged()
            }
        }
    }

}