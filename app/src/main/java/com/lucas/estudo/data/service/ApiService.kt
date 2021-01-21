package com.lucas.estudo.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService(url: String) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun produtoService() = retrofit.create(ProdutoService::class.java)
}