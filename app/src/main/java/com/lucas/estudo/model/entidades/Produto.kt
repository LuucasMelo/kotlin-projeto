package com.lucas.estudo.model.entidades

data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    var thumbnail: String,
    var descricaoId: String,
    var fotos: List<String> = listOf(),
    var quantidade: Int = 0
)