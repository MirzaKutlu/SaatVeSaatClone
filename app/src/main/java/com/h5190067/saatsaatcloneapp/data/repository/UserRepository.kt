package com.h5190067.saatsaatcloneapp.data.repository

import com.h5190067.saatsaatcloneapp.data.datasource.RemoteUserDataSource
import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.util.Resource
import kotlinx.coroutines.flow.Flow

class UserRepository {
    private var userDataSource: RemoteUserDataSource? = null

    init {
        userDataSource = RemoteUserDataSource()
    }

    fun getUsers(): Flow<Resource<UserResponse>> {
        return userDataSource!!.getUsers()
    }
}