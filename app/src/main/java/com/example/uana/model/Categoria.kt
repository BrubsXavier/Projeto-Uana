package com.example.uana.model

data class Categoria(
    var id: Long,
    var nome: String,
    var produtos: List<Produto>?
) {

    override fun toString(): String {
        return nome!!
    }

}