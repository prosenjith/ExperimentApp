package com.example.experimentapp.paging3.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.experimentapp.paging3.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DummyPagingSource : PagingSource<Int, Item>() {
    private val apiService = DummyApiService()
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val response = withContext(Dispatchers.IO) {
                apiService.getItems(page, pageSize)
            }

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}