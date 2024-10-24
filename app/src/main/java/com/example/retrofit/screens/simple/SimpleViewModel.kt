package com.example.retrofit.screens.simple

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Product
import com.example.retrofit.domain.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleViewModel @Inject constructor(
    private val useCase: GetProductUseCase
): ViewModel() {

    fun getProduct(
        user: User,
        product: (Product?, error: String?) -> Unit
    ) {
        viewModelScope.launch {
            Log.d("vmTag", this.toString())

            try {
                val def = async(Dispatchers.IO) {
                    useCase.getProduct(user.token)
                }
                val res = def.await()
                res.onSuccess {
                    product(it, "")
                }.onFailure {
                    product(null, it.message)
                }
            } catch (e: Exception) {
                product(null, e.message)
            }
        }
    }
}