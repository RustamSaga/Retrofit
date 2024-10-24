package com.example.retrofit.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Products
import com.example.retrofit.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
) : ViewModel() {

    fun search(user: User, text: String, searchProducts: (Products, error: String) -> Unit) {
        viewModelScope.launch {
            try {
                val def = async(Dispatchers.IO) {
                    useCase.search(user.token, text)
                }
                def.await().onSuccess {
                    if (it.products.isEmpty()) {
                        searchProducts(it, "Empty List")
                    } else {
                        searchProducts(it, "")
                    }

                }.onFailure {
                    searchProducts(Products(), it.message?: "")
                }
            } catch (e: Exception) {
                searchProducts(Products(), e.message?: "")
            }
        }
    }
}