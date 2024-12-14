package com.example.plantapp.data.dto.remote

import com.example.plantapp.data.dto.remote.dto.category.CategoryDto
import com.example.plantapp.data.dto.remote.dto.question.QuestionDto
import retrofit2.http.GET

interface PlantApi {

    @GET("/getCategories")
    suspend fun getCategories(searhVal: String?): CategoryDto

    @GET("/getQuestions")
    suspend fun getQuestions(): QuestionDto
}