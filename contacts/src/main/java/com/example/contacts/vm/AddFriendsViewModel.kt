package com.example.contacts.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.interfaces.AddFriendContract
import com.example.contacts.presenter.AddFriendPresenter
import com.example.data.repository.FriendsRepository

class AddFriendsViewModel() :
    ViewModel(),
    AddFriendContract.View {
    val presenter = AddFriendPresenter(this)
    val search = MutableLiveData<Boolean>()
    val searchSucFail = MutableLiveData<Boolean>()

    fun search(userName:String?) {
        search.postValue(true)
        presenter.search(userName?:"")
    }

    override fun onSearchStateChange(state: Boolean) {
        searchSucFail.postValue(state)
    }

}

class AddModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddFriendsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddFriendsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
