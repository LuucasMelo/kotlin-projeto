package com.lucas.estudo.model.entidades.singleton

import com.lucas.estudo.model.entidades.Produto

object SingletonCarrinho {

    var produtos: MutableList<Produto> = mutableListOf()
    var valor: Double = produtos.sumByDouble{ it.preco * it.quantidade }

    fun adicionarProduto(produto: Produto){
        val produtoCarrinho = verificarProduto(produto)

        when {
            produtoCarrinho != null -> {
                produtoCarrinho.quantidade ++
            }
            else -> {
                produtos.add(produto)
            }
        }

        totalPedido()
    }

    fun removerProduto(produto: Produto){
        val produtoCarrinho = verificarProduto(produto)

        produtoCarrinho?.let {
            when {
                it.quantidade > 1 -> {
                    it.quantidade--
                }
                else -> {
                    produtos.remove(produto)
                }
            }
        }

        totalPedido()
    }

    fun limparCarrinho(){
        produtos.clear()
        totalPedido()
    }

    private fun verificarProduto(produto: Produto): Produto?{
        return produtos.find { it.id == produto.id }
    }

    private fun totalPedido(){
        valor = produtos.sumByDouble{ it.preco * it.quantidade }
    }
}