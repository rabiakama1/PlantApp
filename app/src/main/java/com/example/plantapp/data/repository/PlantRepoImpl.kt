package com.example.plantapp.data.repository

import com.example.plantapp.data.dto.remote.PlantApi
import com.example.plantapp.data.dto.remote.dto.category.CategoryDto
import com.example.plantapp.data.dto.remote.dto.question.QuestionDto
import com.example.plantapp.domain.repository.PlantRepo
import javax.inject.Inject

class PlantRepoImpl @Inject constructor(
    private val api: PlantApi
) : PlantRepo {

    override suspend fun getCategories(searchVal: String?): CategoryDto {
        return api.getCategories(searchVal)
    }

    override suspend fun getQuestions(): QuestionDto {
        return api.getQuestions()
    }
}