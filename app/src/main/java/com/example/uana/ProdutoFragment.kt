package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uana.adapter.ProdutoAdapter
import com.example.uana.adapter.ProdutoClickListener
import com.example.uana.databinding.FragmentProdutoBinding
import com.example.uana.model.Produto


class ProdutoFragment : Fragment(), ProdutoClickListener {

    private lateinit var binding: FragmentProdutoBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProdutoBinding.inflate(layoutInflater, container, false)

        mainViewModel.listProduto()

        val adapter = ProdutoAdapter(this, mainViewModel, requireContext())
        binding.recyclerProduto.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerProduto.adapter = adapter
        binding.recyclerProduto.setHasFixedSize(true)

        binding.floatingAdd.setOnClickListener {
            mainViewModel.produtoSelecionar = null
            findNavController().navigate(R.id.action_produtoFragment_to_formFragment)
        }


        mainViewModel.myProdutoResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }



        return binding.root
    }

    override fun onProdutoClickListener(produto: Produto) {
        mainViewModel.produtoSelecionar = produto
        findNavController().navigate(R.id.action_produtoFragment_to_formFragment)
    }


}