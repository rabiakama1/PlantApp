package com.example.plantapp.data.dto.remote.dto.category

data class Pagination(
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
)