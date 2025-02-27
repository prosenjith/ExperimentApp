package com.example.experimentapp.paging3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.experimentapp.paging3.data.DummyRepository

class DummyViewModel: ViewModel() {
    private val repository = DummyRepository()
    val items = repository.getItems().cachedIn(viewModelScope)
}