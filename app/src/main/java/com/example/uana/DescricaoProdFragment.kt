package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.uana.databinding.FragmentDescricaoProdBinding
import com.example.uana.databinding.FragmentLoginBinding
import com.example.uana.model.Produto


class DescricaoProdFragment : Fragment() {

    private val mainViewModel:MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDescricaoProdBinding
    var produtoSelecionado: Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDescricaoProdBinding.inflate(layoutInflater, container, false)

        carregarDados()


        return binding.root

    }

    private fun carregarDados() {
        produtoSelecionado = mainViewModel.produtoSelecionar

        if (produtoSelecionado != null) {
            binding.textNome.setText(produtoSelecionado?.nome)
            binding.textPreco.setText(produtoSelecionado?.preco)
            binding.textDescricao.setText(produtoSelecionado?.descricao)
            binding.textCategoria.setText(produtoSelecionado?.categoria?.nome)
            binding.textCodigoProduto.setText(produtoSelecionado?.id.toString())

            Glide
                .with(this)
                .load(produtoSelecionado?.imagemProduto)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(binding.imageLink2)
        }
    }


}