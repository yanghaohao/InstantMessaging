package com.example.contacts.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.bean.Friend
import com.example.data.repository.FriendsRepository
import kotlinx.coroutines.launch

class ContractViewModel(
    val friendsRepository: FriendsRepository,
) : ViewModel() {
    lateinit var allFriend: LiveData<List<Friend>>

    fun insert(friend: Friend) {
        viewModelScope.launch {
            friendsRepository.insert(friend)
        }
    }

    fun getAllFriends(uid: Long,userId:Long) {
        allFriend = friendsRepository.getAllFriends(uid,userId).asLiveData()
    }

    fun delete(friend: Friend) {
        viewModelScope.launch {
            friendsRepository.delete(friend)
        }
    }
}

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
