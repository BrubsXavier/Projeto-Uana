package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentApresentacaoBinding


class ApresentacaoFragment : Fragment() {

    private lateinit var binding: FragmentApresentacaoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentApresentacaoBinding.inflate(layoutInflater, container, false)

        binding.imageView.alpha = 1f

        binding.imageView.animate().setDuration(3000).alpha(1f).withEndAction {
            activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.imageView.animate().setDuration(3000).alpha(1f).withEndAction {
            findNavController().navigate(R.id.action_apresentacaoFragment_to_loginFragment2)
        }

        return binding.root

    }
}
