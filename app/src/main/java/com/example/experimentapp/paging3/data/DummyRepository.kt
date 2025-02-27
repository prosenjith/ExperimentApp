package com.example.experimentapp.paging3.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.experimentapp.paging3.model.Item
import kotlinx.coroutines.flow.Flow

class DummyRepository {
    fun getItems(): Flow<PagingData<Item>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DummyPagingSource() }
        ).flow
    }
}