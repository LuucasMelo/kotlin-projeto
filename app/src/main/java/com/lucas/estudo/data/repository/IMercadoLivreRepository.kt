package com.lucas.estudo.data.repository

import ResponseProdutoDetalhe
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.response.Produto.ResponseProdutos

interface IMercadoLivreRepository {
    fun getProdutos(sellerId: String, produtosCallback: CallbackBase<ResponseProdutos>)
    fun getProdutoDetalhe(productId: String, produtosCallback: CallbackBase<ResponseProdutoDetalhe>)
}