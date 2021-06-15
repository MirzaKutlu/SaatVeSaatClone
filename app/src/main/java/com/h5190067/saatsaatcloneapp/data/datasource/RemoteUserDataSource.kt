package com.h5190067.saatsaatcloneapp.data.datasource

import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteUserDataSource: UserDataSource {
    override fun getUsers(): Flow<Resource<UserResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = CategoryAndUserService.build().getUsers()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}