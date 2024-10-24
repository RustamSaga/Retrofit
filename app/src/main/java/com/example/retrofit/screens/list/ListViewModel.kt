package com.example.retrofit.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Products
import com.example.retrofit.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetProductsUseCase
): ViewModel() {

    fun getAllProducts(
        user: User,
        result: (Products?, error: String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val def = async(Dispatchers.IO) { useCase.getProducts(user.token) }
                def.await().onSuccess {
                    result(it, "")
                }.onFailure {
                    result(null, it.message)
                }

            } catch (e: Exception) {
                result(null, e.message)
            }
        }
    }
}