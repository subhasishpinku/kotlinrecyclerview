package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import com.interview.mykotlinrecyclerview.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllproductList() = retrofitService.getAllproductList()

}