package com.example.plantapp.domain.repository

import com.example.plantapp.data.dto.remote.PlantApi
import com.example.plantapp.data.dto.remote.dto.category.CategoryDto
import com.example.plantapp.data.dto.remote.dto.question.QuestionDto

interface PlantRepo {
    suspend fun getCategories(searchVal: String?): CategoryDto
    suspend fun getQuestions(): QuestionDto
}