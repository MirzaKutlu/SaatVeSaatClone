package com.h5190067.saatsaatcloneapp.data.datasource

import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCategoryDataSource : CategoryDataSource {
    override fun getCategories(): Flow<Resource<CategoriesAndProductsResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = CategoryAndUserService.build().getCategories()

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