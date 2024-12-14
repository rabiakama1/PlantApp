package com.example.plantapp.presentation.home.adapter.question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.data.dto.remote.dto.question.QuestionDtoItem
import com.example.plantapp.databinding.ItemHomeQuestionBinding

class PlantQuestionsRecyclerAdapter(private val questions: List<QuestionDtoItem>) : RecyclerView.Adapter<PlantQuestionsRecyclerAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemHomeQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bindItem(question)
    }

    override fun getItemCount(): Int = questions.size

    inner class QuestionViewHolder(private val binding: ItemHomeQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(q: QuestionDtoItem) {
            binding.imgQuestion
            Glide.with( binding.imgQuestion.context)
                .load(q.imageUri)
                .into(binding.imgQuestion)
            binding.txtQuestion.text = q.title
        }
    }
}