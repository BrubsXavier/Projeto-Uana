package com.example.uana.api

import com.example.uana.model.Categoria
import com.example.uana.model.Produto
import com.example.uana.model.Usuario
import retrofit2.Response

class Repository {

    suspend fun listCategoria(): Response<List<Categoria>> {
        return RetrofitInstance.api.listCategoria()
    }

    suspend fun listCategoriaNome(): Response<List<Categoria>> {
        return RetrofitInstance.api.listCategoriaNome()
    }

    suspend fun addProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.addProduto(produto)
    }

    suspend fun listProduto(): Response<List<Produto>> {
        return RetrofitInstance.api.listProduto()
    }

    suspend fun listProdutoNome(): Response<List<Produto>> {
        return RetrofitInstance.api.listProdutoNome()
    }

    suspend fun listProdutoCodigo(): Response<List<Produto>> {
        return RetrofitInstance.api.listProdutoCodigo()
    }

    suspend fun listProdutoPreco(): Response<List<Produto>> {
        return RetrofitInstance.api.listProdutoPreco()
    }

    suspend fun listProdutoDescricao(): Response<List<Produto>> {
        return RetrofitInstance.api.listProdutoDescricao()
    }

    suspend fun updateProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.updateProduto(produto)
    }

    suspend fun deleteProduto(id: Long): Response<Produto> {
        return RetrofitInstance.api.deleteProduto(id)
    }

    suspend fun consultaUsuarioEmail(email:String) :Response<Usuario>{
        return RetrofitInstance.api.consultaUsuarioEmail(email)
    }

    suspend fun consultaUsuarioSenha(senha: String):Response<Usuario>{
        return RetrofitInstance.api.consultaUsuarioSenha(senha)
    }

    suspend fun addUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.api.addUsuario(usuario)
    }

    suspend fun updateUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.api.updateUsuario(usuario)
    }
}