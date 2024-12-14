package com.example.plantapp.presentation.home

import com.example.plantapp.data.dto.remote.dto.category.CategoryItem
import com.example.plantapp.data.dto.remote.dto.question.QuestionDtoItem

data class HomePageState(
    val isLoading: Boolean = false,
    val questions: List<QuestionDtoItem> = emptyList(),
    var plantCategories: List<CategoryItem> = emptyList(),
    val errorMsg: String ? = null,
    val searchVal: String? = null
)