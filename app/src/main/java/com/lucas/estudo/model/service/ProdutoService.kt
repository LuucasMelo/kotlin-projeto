package com.lucas.estudo.model.service

import ProdutoDescricao
import ResponseProdutoDetalhe
import com.lucas.estudo.model.entidades.response.Produto.ResponseProdutos
import com.lucas.estudo.model.entidades.response.ProdutoDescricao.ResponseProdutoDescricao
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

    @GET("items/{ProductID}/descriptions")
    fun produtoDescricao(
            @Path("ProductID") ProductID: String,
    ): Call<List<ProdutoDescricao>>
}