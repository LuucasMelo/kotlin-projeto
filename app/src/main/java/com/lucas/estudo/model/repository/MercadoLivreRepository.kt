package com.lucas.estudo.model.repository

import ProdutoDescricao
import ResponseProdutoDetalhe
import com.lucas.estudo.model.CallbackBase
import com.lucas.estudo.model.CallbackResult
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.entidades.response.Produto.ResponseProdutos
import com.lucas.estudo.model.entidades.response.ProdutoDescricao.ResponseProdutoDescricao
import com.lucas.estudo.model.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MercadoLivreRepository: IMercadoLivreRepository {
    override fun getProdutosVendedor(sellerId: String, produtosCallback: CallbackResult<List<Produto>>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtosVendedor(sellerId)
            .enqueue(object : Callback<ResponseProdutos> {
                override fun onResponse(
                    call: Call<ResponseProdutos>,
                    response: Response<ResponseProdutos>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let {
                                produtosCallback.Success(it.toListOfProdutos())
                            }
                        }
                        else -> {
                            produtosCallback.ApiError(
                                ErroApi(
                                    response.code(),
                                    response.message()
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseProdutos>, t: Throwable) {
                    produtosCallback.ServerError()
                }
            })
    }

    override fun getProdutos(query: String, produtosCallback: CallbackResult<List<Produto>>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtos(query).enqueue(object : Callback<ResponseProdutos> {
            override fun onResponse(call: Call<ResponseProdutos>, response: Response<ResponseProdutos>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { produtosCallback.Success(it.toListOfProdutos()) }
                    }
                    else -> {
                        produtosCallback.ApiError(
                            ErroApi(
                                response.code(),
                                response.message()
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseProdutos>, t: Throwable) {
                produtosCallback.ServerError()
            }
        })
    }

    override fun getProdutoDetalhe(productId: String, produtosCallback: CallbackResult<Produto>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtoDetalhe(productId).enqueue(object : Callback<ResponseProdutoDetalhe> {
            override fun onResponse(call: Call<ResponseProdutoDetalhe>, response: Response<ResponseProdutoDetalhe>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { produtosCallback.Success(it.toProduto()) }
                    }
                    else -> {
                        produtosCallback.ApiError(ErroApi(response.code(), response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseProdutoDetalhe>, t: Throwable) {
                produtosCallback.ServerError()
            }
        })
    }

    override fun getProdutoDescricao(productId: String, produtosCallback: CallbackResult<List<ProdutoDescricao>>) {
        ApiService("https://api.mercadolibre.com/").produtoService().produtoDescricao(productId).enqueue(object : Callback<List<ProdutoDescricao>> {
            override fun onResponse(call: Call<List<ProdutoDescricao>>, response: Response<List<ProdutoDescricao>>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { produtosCallback.Success(it) }
                    }
                    else -> {
                        produtosCallback.ApiError(ErroApi(response.code(), response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<List<ProdutoDescricao>>, t: Throwable) {
                produtosCallback.ServerError()
            }
        })
    }
}