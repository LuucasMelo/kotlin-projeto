package com.lucas.estudo.data.repository

import ResponseProdutoDetalhe
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.model.ErroApi
import com.lucas.estudo.data.response.Produto.ResponseProdutos
import com.lucas.estudo.data.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MercadoLivreRepository: IMercadoLivreRepository {
    override fun getProdutos(sellerId: String, produtosCallback: CallbackBase<ResponseProdutos>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtos(sellerId).enqueue(object : Callback<ResponseProdutos> {
            override fun onResponse(call: Call<ResponseProdutos>, response: Response<ResponseProdutos>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { produtosCallback.onSuccess(it) }
                    }
                    else -> {
                        produtosCallback.onError(ErroApi(response.code().toString(), response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseProdutos>, t: Throwable) {

            }
        })
    }

    override fun getProdutoDetalhe(productId: String, produtosCallback: CallbackBase<ResponseProdutoDetalhe>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtoDetalhe(productId).enqueue(object : Callback<ResponseProdutoDetalhe> {
            override fun onResponse(call: Call<ResponseProdutoDetalhe>, response: Response<ResponseProdutoDetalhe>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { produtosCallback.onSuccess(it) }
                    }
                    else -> {
                        produtosCallback.onError(ErroApi(response.code().toString(), response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseProdutoDetalhe>, t: Throwable) {
                var x = ""
                produtosCallback.onError(ErroApi("500", t.message!!))
            }
        })
    }
}