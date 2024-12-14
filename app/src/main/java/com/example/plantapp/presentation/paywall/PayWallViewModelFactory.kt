package com.example.plantapp.presentation.paywall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantapp.data.dto.locale.UserDao
import com.example.plantapp.presentation.paywall.model.PayWallRepository

class PayWallViewModelFactory(
    private val dao: UserDao,
    private val repository: PayWallRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PayWallViewModel::class.java)) {
            return PayWallViewModel(dao,repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}