package com.lucas.estudo.model.repository

import ProdutoDescricao
import ResponseProdutoDetalhe
import com.lucas.estudo.model.CallbackBase
import com.lucas.estudo.model.CallbackResult
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.entidades.response.Produto.ResponseProdutos

interface IMercadoLivreRepository {
    fun getProdutosVendedor(sellerId: String, produtosCallback: CallbackResult<List<Produto>>)
    fun getProdutos(query: String = "", produtosCallback: CallbackResult<List<Produto>>)
    fun getProdutoDetalhe(productId: String, produtosCallback: CallbackResult<Produto>)
    fun getProdutoDescricao(productId: String, produtosCallback: CallbackResult<List<ProdutoDescricao>>)
}