package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.uana.databinding.FragmentCategoriaBinding


class CategoriaFragment : Fragment() {

    private lateinit var binding: FragmentCategoriaBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoriaBinding.inflate(layoutInflater, container, false)

        mainViewModel.listProduto()
        mainViewModel.listCategoriaNome()

        var categoria = mainViewModel.listCategoriaNome()

        var produto = mainViewModel.listProduto()

        binding.textAcessorios.setOnClickListener {


        }

        return binding.root
    }

}