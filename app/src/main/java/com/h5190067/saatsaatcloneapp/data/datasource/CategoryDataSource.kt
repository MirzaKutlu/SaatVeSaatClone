package com.h5190067.saatsaatcloneapp.data.datasource

import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun getCategories(): Flow<Resource<CategoriesAndProductsResponse>>
}