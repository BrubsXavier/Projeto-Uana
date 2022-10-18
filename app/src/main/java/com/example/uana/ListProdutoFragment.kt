package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uana.adapter.ProdutoAdapter
import com.example.uana.adapter.ProdutoClickListener
import com.example.uana.adapter.ProdutoListAdapter
import com.example.uana.databinding.FragmentListProdutoBinding
import com.example.uana.model.Produto


class ListProdutoFragment : Fragment(), ProdutoClickListener {

    private lateinit var binding: FragmentListProdutoBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListProdutoBinding.inflate(layoutInflater, container, false)

        mainViewModel.listProduto()

        val adapter = ProdutoListAdapter(this, mainViewModel, requireContext())
        binding.recyclerProduto.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerProduto.adapter = adapter
        binding.recyclerProduto.setHasFixedSize(true)


        mainViewModel.myProdutoResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }


        return binding.root
    }

    override fun onProdutoClickListener(produto: Produto) {
        mainViewModel.produtoSelecionar = produto
        findNavController().navigate(R.id.action_listProdutoFragment_to_descricaoProdFragment)
    }
}
