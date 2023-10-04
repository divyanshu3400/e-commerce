package com.cloudsect.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {


    private var categoryRepository: HomeRepository = HomeRepository()

    fun getBrands(): LiveData<List<BrandModel>> {
        return categoryRepository.getBrand()
    }
}