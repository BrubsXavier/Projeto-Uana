package com.example.uana


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentFormBinding
import com.example.uana.model.Categoria
import com.example.uana.model.Produto


class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L
    private var produtoSelecionado: Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {


        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listCategoria()

        nomeFocusListener()
        precoFocusListener()
        estoqueFocusListener()
        descricaoFocusListener()
        imagemProdutoFocusListener()



        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())
            spinnerCategoria(response.body())
        }

        binding.buttonSalvar.setOnClickListener {
            submittForm()
        }

        binding.buttonCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_produtoFragment)
        }



        return binding.root
    }

    private fun nomeFocusListener() {
        binding.editNome.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nomeContainer.helperText = validNome()
            }
        }
    }

    private fun validNome(): String? {
        val nomeText = binding.editNome.text.toString()
        if (nomeText == "" || nomeText.length < 3 || nomeText.length > 20) {

            return "Digite um nome válido"
        }
        return null
    }


    private fun precoFocusListener() {
        binding.editPreco.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.precoContainer.helperText = validPreco()
            }
        }
    }

    private fun validPreco(): String? {
        val preco = binding.editPreco.text.toString()
        if (preco == "" || preco <= 0.toString() || preco.length > 5) {
            return "Digite um preço válido"
        }
        return null
    }

    private fun estoqueFocusListener() {
        binding.editEstoque.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.estoqueContainer.helperText = validEstoque()
            }
        }
    }

    private fun validEstoque(): String? {
        val estoque = binding.editEstoque.text.toString()
        if (estoque.isNotEmpty() || estoque.isNotBlank()) {
            var estoqueInt = estoque.toInt()

            if (estoqueInt < 0 || estoqueInt > 100000) {
                return "Digite um valor válido"
            }
        }


        return null
    }

    private fun descricaoFocusListener() {

        binding.editDescricao.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.descricaoContainer.helperText = validDescricao()
            }
        }
    }

    private fun validDescricao(): String? {

        val descricao = binding.editDescricao.text.toString()
        if (descricao == "" || descricao.length < 5 || descricao.length > 2000) {
            return "Digite um a descrição válida"
        }
        return null
    }

    private fun imagemProdutoFocusListener() {

        binding.editImagemProduto.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.linkContainer.helperText = validImagemProduto()
            }
        }
    }

    private fun validImagemProduto(): String? {

        val imagemProduto = binding.editImagemProduto.text.toString()
        if (imagemProduto == "" || imagemProduto.length < 10) {
            return "Digite um link válido"
        }
        return null
    }

    private fun submittForm() {

        binding.nomeContainer.helperText = validNome()
        binding.precoContainer.helperText = validPreco()
        binding.estoqueContainer.helperText = validEstoque()
        binding.descricaoContainer.helperText = validDescricao()
        binding.linkContainer.helperText = validImagemProduto()


        val validNome = binding.nomeContainer.helperText == null
        val validPreco = binding.precoContainer.helperText == null
        val validEstoque = binding.estoqueContainer.helperText == null
        val validDesc = binding.descricaoContainer.helperText == null
        val validLink = binding.linkContainer.helperText == null

        if (validNome && validPreco && validEstoque && validDesc && validLink) {
            enviarForm()
        } else {
            invalidForm()
        }
    }

    private fun invalidForm() {
        var message = ""

        if (binding.nomeContainer.helperText != null) {
            message += "\n\nEmail: " + binding.nomeContainer.helperText
        }

        if (binding.precoContainer.helperText != null) {
            message += "\n\nPreço: " + binding.precoContainer.helperText
        }

        if (binding.estoqueContainer.helperText != null) {
            message += "\n\nEstoque: " + binding.estoqueContainer.helperText
        }

        if (binding.descricaoContainer.helperText != null) {
            message += "\n\nDescrição: " + binding.descricaoContainer.helperText
        }

        if (binding.linkContainer.helperText != null) {
            message += "\n\nLink: " + binding.linkContainer.helperText
        }

        AlertDialog.Builder(context).setTitle("Cadastro Inválido").setMessage(message)
            .setPositiveButton("Ok") { _, _ ->
                //faça nada
            }.show()

    }


    private fun enviarForm() {

        val nome = binding.editNome.text.toString()
        val desc = binding.editDescricao.text.toString()
        val price = binding.editPreco.text.toString()
        val imagem = binding.editImagemProduto.text.toString()
        val estoq = binding.editEstoque.text.toString()
        val categoria = Categoria(categoriaSelecionada, " ", null)


        var message = "Email:" + binding.editNome.text
        message += "\nPreço:" + binding.editPreco.text
        message += "\nEstoque:" + binding.editEstoque.text
        message += "\nDescrição:" + binding.editDescricao.text
        message += "\nLink:" + binding.editImagemProduto.text

        if (produtoSelecionado != null) {

            AlertDialog.Builder(context).setTitle("Produto Atualizado").setMessage(message)
                .setPositiveButton("Produto atualizado com Sucesso!") { _, _ ->
                    binding.editNome.text != null
                    binding.editPreco.text != null
                    binding.editEstoque.text != null
                    binding.editDescricao.text != null
                    binding.editImagemProduto.text != null

                    binding.nomeContainer.helperText = getString(R.string.Required)
                    binding.precoContainer.helperText = getString(R.string.Required)
                    binding.estoqueContainer.helperText = getString(R.string.Required)
                    binding.descricaoContainer.helperText = getString(R.string.Required)
                    binding.linkContainer.helperText = getString(R.string.Required)

                }.show()

            val produto = Produto(
                produtoSelecionado?.id!!,
                nome,
                desc,
                0,
                price,
                imagem,
                estoq.toInt(),
                categoria
            )
            mainViewModel.addProduto(produto)
        } else {

            AlertDialog.Builder(context).setTitle("Produto Criado").setMessage(message)
                .setPositiveButton("Produto criado com Sucesso!") { _, _ ->
                    binding.editNome.text = null
                    binding.editPreco.text = null
                    binding.editEstoque.text = null
                    binding.editDescricao.text = null
                    binding.editImagemProduto.text = null

                    binding.nomeContainer.helperText = getString(R.string.Required)
                    binding.precoContainer.helperText = getString(R.string.Required)
                    binding.estoqueContainer.helperText = getString(R.string.Required)
                    binding.descricaoContainer.helperText = getString(R.string.Required)
                    binding.linkContainer.helperText = getString(R.string.Required)

                }.show()

            val produto = Produto(0, nome, desc, 0, price, imagem, estoq.toInt(), categoria)
            mainViewModel.addProduto(produto)
        }
        findNavController().navigate(R.id.action_formFragment_to_produtoFragment)


    }

    private fun carregarDados() {
        produtoSelecionado = mainViewModel.produtoSelecionar
        var estoque = produtoSelecionado?.estoque

        if (produtoSelecionado != null) {
            binding.editNome.setText(produtoSelecionado?.nome)
            binding.editPreco.setText(produtoSelecionado?.preco)
            binding.editEstoque.setText(estoque!!.toString())
            binding.editDescricao.setText(produtoSelecionado?.descricao)
            binding.editImagemProduto.setText(produtoSelecionado?.imagemProduto)
        }
    }


    private fun spinnerCategoria(listCategoria: List<Categoria>?) {

        if (listCategoria != null) {
            binding.spinnerCategoria.adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listCategoria
            )
            binding.spinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.spinnerCategoria.selectedItem as Categoria
                        categoriaSelecionada = selected.id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
        }

    }

}


