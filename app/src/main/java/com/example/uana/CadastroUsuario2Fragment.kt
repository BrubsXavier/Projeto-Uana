package com.example.uana

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentCadastroUsuario2Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import hilt_aggregated_deps._com_example_uana_MainViewModel_HiltModules_BindsModule


class CadastroUsuario2Fragment : Fragment() {

    private lateinit var binding: FragmentCadastroUsuario2Binding
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCadastroUsuario2Binding.inflate(layoutInflater, container, false)

        cepFocusListener()
        enderecoFocusListener()
        numeroFocusListener()
        bairroFocusListener()
        cidadeFocusListener()

        binding.buttonNext.isEnabled = false

        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())

        binding.checkBox.setOnCheckedChangeListener { compoundButton, checado ->
            if (checado) {
                materialAlertDialogBuilder.setTitle("Termos e Condições: ")
                materialAlertDialogBuilder.setMessage("Quando você usa nossos serviços, está confiando a nós suas informações. Entendemos que isso é uma grande responsabilidade e trabalhamos duro para proteger essas informações e colocar você no controle.")
                materialAlertDialogBuilder.setPositiveButton(
                    "Aceitar"
                ) { dialogInterface, i ->
                    binding.buttonNext.isEnabled = true
                    dialogInterface.dismiss()
                }
                materialAlertDialogBuilder.setNegativeButton(
                    "Recusar"
                ) { dialogInterface, i ->
                    dialogInterface.dismiss()
                    binding.checkBox.isChecked = false
                }.show()
            } else {
                binding.buttonNext.isEnabled = false
            }
        }


        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroUsuario2Fragment_to_homeFragment)
        }

        binding.buttonVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroUsuario2Fragment_to_cadastroUsuarioFragment)
        }

        return binding.root
    }

    private fun cepFocusListener() {
        binding.editCep.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.cepContainer.helperText = validCep()
            }
        }
    }

    private fun validCep(): String? {
        val editCep = binding.editCep.text.toString()
        if (!editCep.matches(".*[0-9].*".toRegex())) {
            return "O CEP só deve conter números!"
        }
        if (editCep.length != 8) {
            return "O CEP deve conter apenas números!"
        }
        return null
    }

    private fun enderecoFocusListener() {
        binding.editEndereco.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.enderecoContainer.helperText = validEndereco()
            }
        }
    }

    private fun validEndereco(): String? {
        val editEndereco = binding.editEndereco.text.toString()
        if (editEndereco == "") {
            return "Digite um endereço válido!"
        }
        return null
    }

    private fun numeroFocusListener() {
        binding.editNumero.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.numeroContainer.helperText = validNumero()
            }
        }
    }

    private fun validNumero(): String? {
        val editNumero = binding.editNumero.text.toString()
        if (!editNumero.matches(".*[0-9].*".toRegex())) {
            return "Digite um valor válido!"
        }
        return null
    }

    private fun bairroFocusListener() {
        binding.editBairro.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.bairroContainer.helperText = validBairro()
            }
        }
    }

    private fun validBairro(): String? {
        val editBairro = binding.editBairro.text.toString()
        if (editBairro == "" || editBairro.length < 3) {
            return "Digite um valor válido!"
        }
        return null
    }

    private fun cidadeFocusListener() {
        binding.editCidade.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.cidadeContainer.helperText = validCidade()
            }
        }
    }

    private fun validCidade(): String? {
        val editCidade = binding.editCidade.text.toString()
        if (editCidade == "" || editCidade.length < 3 || editCidade.length > 30) {
            return "Digite uma cidade válida!"
        }
        return null
    }

    private fun estadoFocusListener() {
        binding.editEstado.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.estadoContainer.helperText = validEstado()
            }
        }
    }

    private fun validEstado(): String? {
        val editEstado = binding.editEstado.text.toString()
        if (editEstado == "" || editEstado.length < 2 || editEstado.length > 30) {
            return "Digite uma cidade válida!"
        }
        return null
    }


}
