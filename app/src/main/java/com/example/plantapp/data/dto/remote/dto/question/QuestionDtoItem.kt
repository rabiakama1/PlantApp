package com.example.plantapp.data.dto.remote.dto.question

data class QuestionDtoItem(
    val id: Int,
    val imageUri: String,
    val order: Int,
    val subtitle: String,
    val title: String,
    val uri: String
)