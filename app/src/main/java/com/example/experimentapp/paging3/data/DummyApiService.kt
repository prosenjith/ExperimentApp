package com.example.experimentapp.paging3.data

import com.example.experimentapp.paging3.model.ApiResponse
import com.example.experimentapp.paging3.model.Item
import kotlinx.coroutines.delay

class DummyApiService {

    // Example: https://your-api.com/items?page=1&per_page=10
    suspend fun getItems(
        page: Int,
        pageSize: Int
    ): ApiResponse {
        val items = (1..pageSize).map {
            Item(it + (page - 1) * pageSize, "Item #${it + (page - 1) * pageSize}")
        }
        delay(2000)
        return ApiResponse(
            data = items,
            page = page,
            totalPages = 10
        )
    }
}