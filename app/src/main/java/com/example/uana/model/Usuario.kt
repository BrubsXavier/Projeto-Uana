package com.example.uana.model

import java.util.*

data class Usuario(
    val id: Long,
    val cpf: Long,
    val nome: String,
    val nascimento: String,
    val genero: Boolean,
    val email: String,
    val endereco: String,
    val senha: String,
    val telefone: Long
        ){

    var favoritos = 0
}