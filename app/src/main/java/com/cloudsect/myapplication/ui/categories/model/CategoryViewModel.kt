package com.cloudsect.myapplication.ui.categories.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cloudsect.myapplication.ui.categories.CategoryRepository

class CategoryViewModel(application: Application):AndroidViewModel(application) {

    private var categoryRepository: CategoryRepository = CategoryRepository()

    fun getNavItems(): LiveData<List<CategoryResponse>> {
        return categoryRepository.getNavItemList()
    }    fun getNavChildItems(childCatReq: ChildCatReq): LiveData<List<ChildCatRes>> {
        return categoryRepository.getNavChildItemList(childCatReq)
    }
}