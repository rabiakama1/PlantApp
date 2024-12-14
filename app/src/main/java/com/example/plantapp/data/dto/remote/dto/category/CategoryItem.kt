package com.example.plantapp.data.dto.remote.dto.category

data class CategoryItem(
    val createdAt: String,
    val id: Int,
    val image: Image,
    val name: String,
    val publishedAt: String,
    val rank: Int,
    val title: String,
    val updatedAt: String
)