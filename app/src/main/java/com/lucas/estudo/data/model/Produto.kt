package com.lucas.estudo.data.model

data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    var thumbnail: String,
    var quantidade: Int = 0
)