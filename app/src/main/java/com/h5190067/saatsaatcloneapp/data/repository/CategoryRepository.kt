package com.h5190067.saatsaatcloneapp.data.repository

import com.h5190067.saatsaatcloneapp.data.datasource.CategoryDataSource
import com.h5190067.saatsaatcloneapp.data.datasource.RemoteCategoryDataSource
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.util.Resource
import kotlinx.coroutines.flow.Flow

class CategoryRepository {
    private var categoryDataSource: CategoryDataSource?=null

    init {
        categoryDataSource= RemoteCategoryDataSource()
    }

    fun getCategories(): Flow<Resource<CategoriesAndProductsResponse>>
    {
        return categoryDataSource!!.getCategories()
    }
}