package com.example.uana

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uana.api.Repository
import com.example.uana.model.Categoria
import com.example.uana.model.Produto
import com.example.uana.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (private val repository: Repository) : ViewModel() {

    val dataSelecionada = MutableLiveData<LocalDate>()

    val usuarioLog = MutableLiveData<Response<Usuario>>()

    var produtoSelecionar: Produto? = null

    private val _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse: LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse


    private val _myProdutoResponse =
        MutableLiveData<Response<List<Produto>>>()

    val myProdutoResponse: LiveData<Response<List<Produto>>> =
        _myProdutoResponse


    fun listCategoria() {
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listCategoriaNome() {
        viewModelScope.launch {
            try {
                val response = repository.listCategoriaNome()
                _myCategoriaResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                val response = repository.addProduto(produto)
                Log.d("Sucesso!", response.body().toString())
                listProduto()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listProduto() {

        viewModelScope.launch {
            try {
                val response = repository.listProduto()
                _myProdutoResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun listProdutoNome() {

        viewModelScope.launch {
            try {
                val response = repository.listProdutoNome()
                _myProdutoResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun listProdutoCodigo() {

        viewModelScope.launch {
            try {
                val response = repository.listProdutoCodigo()
                _myProdutoResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun listProdutoPreco() {

        viewModelScope.launch {
            try {
                val response = repository.listProdutoPreco()
                _myProdutoResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun listProdutoDescricao() {

        viewModelScope.launch {
            try {
                val response = repository.listProdutoDescricao()
                _myProdutoResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun updateProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                repository.updateProduto(produto)
                listProduto()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun deleteProduto(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteProduto(id)
                listProduto()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun consultaUsuarioEmail(email: String) {
        viewModelScope.launch {
            try {
                val response = repository.consultaUsuarioEmail(email)
                usuarioLog.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun consultaUsuarioSenha(senha: String) {
        viewModelScope.launch {
            try {
                val response = repository.consultaUsuarioSenha(senha)
                usuarioLog.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response = repository.addUsuario(usuario)
                Log.d("Sucesso!", response.body().toString())
                listProduto()

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateUsuario(usuario: Usuario) {
        viewModelScope.launch {

            try {
                repository.updateUsuario(usuario)
                listProduto()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }


}


