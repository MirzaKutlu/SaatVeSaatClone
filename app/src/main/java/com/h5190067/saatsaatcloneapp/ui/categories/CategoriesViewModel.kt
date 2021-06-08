package com.h5190067.saatsaatcloneapp.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.data.repository.CategoryRepository
import com.h5190067.saatsaatcloneapp.util.ResourceStatus
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel(){

    private  val categoryRepository: CategoryRepository = CategoryRepository()

    init {
        getCategories()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var allCategoriesLiveData = MutableLiveData<CategoriesAndProductsResponse>()
    var error =    MutableLiveData<Throwable>()


    fun getCategories()  = viewModelScope.launch {

        categoryRepository.getCategories()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("Mirza",it.data.toString())
                        allCategoriesLiveData.postValue(it.data!!)
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