package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentHomeBinding
import com.example.uana.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        binding.cardAtualizarProduto.setOnClickListener{

            findNavController().navigate(R.id.action_menuFragment_to_produtoFragment)

        }

        binding.cardAddProduto.setOnClickListener{

            mainViewModel.produtoSelecionar = null
            findNavController().navigate(R.id.action_menuFragment_to_formFragment)

        }

        binding.cardSuporte.setOnClickListener {

            findNavController().navigate(R.id.action_menuFragment_to_supportFragment)
        }

        binding.cardSobreNos.setOnClickListener {

            findNavController().navigate(R.id.action_menuFragment_to_sobreNosFragment)
        }

        binding.buttonLogOut.setOnClickListener {

            findNavController().navigate(R.id.action_menuFragment_to_apresentacaoFragment)

        }


        return binding.root

    }
}
