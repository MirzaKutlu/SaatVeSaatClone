package com.h5190067.saatsaatcloneapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.data.repository.UserRepository
import com.h5190067.saatsaatcloneapp.util.ResourceStatus
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    init {
        getUsers()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var allUsersLiveData = MutableLiveData<UserResponse>()
    var error = MutableLiveData<Throwable>()

    private fun getUsers() = viewModelScope.launch {
        userRepository.getUsers()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("Veri", it.data.toString())
                        allUsersLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}