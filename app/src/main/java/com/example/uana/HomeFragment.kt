package com.example.uana


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uana.adapter.ProdutoAdapter
import com.example.uana.adapter.ProdutoClickListener
import com.example.uana.adapter.ProdutoListAdapter
import com.example.uana.databinding.FragmentHomeBinding
import com.example.uana.model.Produto
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ProdutoClickListener  {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mainViewModel.listProduto()

        val adapter = ProdutoListAdapter(this, mainViewModel, requireContext())

        binding.carouselRecyclerview.adapter = adapter
        binding.carouselRecyclerview.apply {
            set3DItem(false)
            setAlpha(false)
            setInfinite(true)
            setFlat(true)
        }



        mainViewModel.myProdutoResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }

        binding.buttonDoeAgora.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_doacaoFragment)

        }

        binding.buttonCompreAgora.setOnClickListener {

            findNavController().navigate((R.id.action_homeFragment_to_listProdutoFragment))
        }

        return binding.root
    }

    override fun onProdutoClickListener(produto: Produto) {
        mainViewModel.produtoSelecionar = produto
        findNavController().navigate(R.id.action_homeFragment_to_descricaoProdFragment)
    }
}