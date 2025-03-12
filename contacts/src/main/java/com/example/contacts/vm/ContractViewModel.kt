package com.example.contacts.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.FriendsRepository

class ContractViewModel(
    val friendsRepository: FriendsRepository,
) : ViewModel()

class ContractModelFactory(
    private val repository: FriendsRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContractViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContractViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
