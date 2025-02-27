package com.example.experimentapp.paging3.model

data class ApiResponse(
    val data: List<Item>,
    val page: Int,
    val totalPages: Int
)