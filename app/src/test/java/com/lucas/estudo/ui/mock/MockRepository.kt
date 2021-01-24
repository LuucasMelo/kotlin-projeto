package com.lucas.estudo.ui.mock

import ProdutoDescricao
import ResponseProdutoDetalhe
import com.lucas.estudo.model.CallbackResult
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.repository.IMercadoLivreRepository

class MockRepository(val onResult: String): IMercadoLivreRepository {
    lateinit var produtos: List<Produto>
    lateinit var produto: Produto
    lateinit var produtoDescricao: List<ProdutoDescricao>
    lateinit var errorApi: ErroApi

    override fun getProdutosVendedor(sellerId: String, produtosCallback: CallbackResult<List<Produto>>) {

        when(onResult){
            "Success" -> {
                produtosCallback.Success(produtos)
            }
            "ApiError" -> {
                produtosCallback.ApiError(errorApi)
            }
            "ServerError" -> {
                produtosCallback.ServerError()
            }
        }

    }

    override fun getProdutos(query: String, produtosCallback: CallbackResult<List<Produto>>) {
        when(onResult){
            "Success" -> {
                produtosCallback.Success(produtos)
            }
            "ApiError" -> {
                produtosCallback.ApiError(errorApi)
            }
            "ServerError" -> {
                produtosCallback.ServerError()
            }
        }
    }

    override fun getProdutoDetalhe(productId: String, produtosCallback: CallbackResult<Produto>) {
        when(onResult){
            "Success" -> {
                produtosCallback.Success(produto)
            }
            "ApiError" -> {
                produtosCallback.ApiError(errorApi)
            }
            "ServerError" -> {
                produtosCallback.ServerError()
            }
        }
    }

    override fun getProdutoDescricao(productId: String, produtosCallback: CallbackResult<List<ProdutoDescricao>>) {
        when(onResult){
            "Success" -> {
                produtosCallback.Success(produtoDescricao)
            }
            "ApiError" -> {
                produtosCallback.ApiError(errorApi)
            }
            "ServerError" -> {
                produtosCallback.ServerError()
            }
        }
    }

}