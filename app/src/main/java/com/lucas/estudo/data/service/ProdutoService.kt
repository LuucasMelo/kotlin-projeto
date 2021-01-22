package com.lucas.estudo.data.service

import ResponseProdutoDetalhe
import com.lucas.estudo.data.response.Produto.ResponseProdutos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProdutoService {
    @GET("sites/MLB/search")
    fun produtosVendedor(
            @Query("seller_id") seller_id: String
            ): Call<ResponseProdutos>

    @GET("sites/MLB/search")
    fun produtos(
            @Query("q") query: String
    ): Call<ResponseProdutos>

    @GET("items/{ProductID}")
    fun produtoDetalhe(
            @Path("ProductID") ProductID: String,
    ): Call<ResponseProdutoDetalhe>
}