package com.example.experimentapp.animatedsearch

import android.os.Bundle
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.experimentapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class SearchActivity : AppCompatActivity() {
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        autoCompleteTextView = findViewById(R.id.etSearchShops)

        // Start the hint animation
        lifecycleScope.launch {
            fetchRecentSearchesFromApi()
                .collect { recentSearches ->
                    animateTypingHints(recentSearches)
                }
        }
    }

    private suspend fun animateTypingHints(hints: List<String>) {
        var index = 0
        while (coroutineContext.isActive) {
            val currentHint = hints[index]
            typeHint(currentHint)
            delay(3000) // Wait before changing hint
            index = (index + 1) % hints.size
        }
    }

    private suspend fun typeHint(hintText: String) {
        withContext(Dispatchers.Main) {
            autoCompleteTextView.text?.clear()
            autoCompleteTextView.hint = ""

            for (i in hintText.indices) {
                autoCompleteTextView.hint = hintText.substring(0, i + 1)
                delay(80) // Faster typing effect
            }
        }
    }

    private fun fetchRecentSearchesFromApi(): Flow<List<String>> {
        val recentSearches = listOf(
            "Laptop", "Mobile Phone", "Wireless Earbuds", "Smartwatch",
            "Gaming Console", "Bluetooth Speaker", "Camera", "Headphones",
            "Tablet", "Smart TV"
        )
        return flow {
            delay(1000) // Simulate network call
            emit(recentSearches)
        }.flowOn(Dispatchers.IO)
    }
}
