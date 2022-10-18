package com.example.uana.api

import com.example.uana.model.Categoria
import com.example.uana.model.Produto
import com.example.uana.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("categoria")
    suspend fun listCategoria(): Response<List<Categoria>>

    @GET("categoria/nome/{nome}")
    suspend fun listCategoriaNome(): Response<List<Categoria>>

    @POST("produto")
    suspend fun addProduto(
        @Body produto: Produto
    ): Response<Produto>

    @GET("produto")
    suspend fun listProduto(): Response<List<Produto>>

    @GET("produto/nome/{nome}")
    suspend fun listProdutoNome(): Response<List<Produto>>

    @GET("produto/codigo/{codigo}")
    suspend fun listProdutoCodigo(): Response<List<Produto>>

    @GET("produto/preco/{preco}")
    suspend fun listProdutoPreco(): Response<List<Produto>>

    @GET("produto/descricao/{descricao}")
    suspend fun listProdutoDescricao(): Response<List<Produto>>

    @PUT("produto")
    suspend fun updateProduto(
        @Body produto: Produto
    ): Response<Produto>

    @DELETE("produto/{id}")
    suspend fun deleteProduto(
        @Path("id") id: Long
    ): Response<Produto>

    @GET("usuario/email/{email}")
    suspend fun consultaUsuarioEmail(
        @Path("email") email: String
    ): Response<Usuario>

    @GET("usuario/senha/{senha}")
    suspend fun consultaUsuarioSenha(
        @Path("senha") senha: String
    ): Response<Usuario>

    @POST("usuario")
    suspend fun addUsuario(
        @Body usuario: Usuario
    ): Response<Usuario>

    @PUT
    suspend fun updateUsuario(
        @Body usuario: Usuario
    ): Response<Usuario>

}