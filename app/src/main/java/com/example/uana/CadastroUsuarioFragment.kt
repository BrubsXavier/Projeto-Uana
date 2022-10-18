package com.example.uana

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentCadastrousuario1Binding
import com.example.uana.model.Usuario
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CadastroUsuarioFragment : Fragment() {

    private lateinit var binding: FragmentCadastrousuario1Binding
    var formatDate = SimpleDateFormat("dd MM yyyy", Locale.ENGLISH)
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCadastrousuario1Binding.inflate(layoutInflater, container, false)

        nomeCompletoFocusListener()
        emailFocusListener()
        passwordFocusListener()
        cpfFocusListener()
        phoneFocusListener()
        dataNascFocusListener()

        binding.buttonNext.setOnClickListener {
            submittForm()
        }

        binding.buttonVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroUsuarioFragment_to_loginFragment2)
        }

        binding.editdataNasc.setOnClickListener {

            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
                    val date: String = formatDate.format(selectDate.time)
                    Toast.makeText(requireContext(), "Data :" + date, Toast.LENGTH_SHORT).show()
                    binding.editdataNasc.setText(date)


                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        return binding.root
    }

    private fun submittForm() {

        binding.nomeContainer.helperText = validNomeCompleto()
        binding.emailContainer.helperText = validEmail()
        binding.senhaContainer.helperText = validPassword()
        binding.cpfContainer.helperText = validCpf()
        binding.telContainer.helperText = validPhone()
        binding.dataNascContainer.helperText = validDataNasc()


        val validNome = binding.nomeContainer.helperText == null
        val validEmail = binding.emailContainer.helperText == null
        val validSenha = binding.senhaContainer.helperText == null
        val validCpf = binding.cpfContainer.helperText == null
        val validNasc = binding.dataNascContainer.helperText == null

        if (validNome && validEmail && validSenha && validCpf && validNasc) {
            enviarForm()
        } else {
            invalidForm()
        }
    }

    private fun invalidForm() {
        var message = ""

        if (binding.nomeContainer.helperText != null) {
            message += "\n\nNome: " + binding.nomeContainer.helperText
        }

        if (binding.emailContainer.helperText != null) {
            message += "\n\nE-mail: " + binding.emailContainer.helperText
        }

        if (binding.senhaContainer.helperText != null) {
            message += "\n\nSenha: " + binding.senhaContainer.helperText
        }

        if (binding.cpfContainer.helperText != null) {
            message += "\n\nCPF: " + binding.cpfContainer.helperText
        }

        if (binding.dataNascContainer.helperText != null) {
            message += "\n\nData Nascimento: " + binding.dataNascContainer.helperText
        }

        if (binding.telContainer.helperText != null) {
            message += "\n\nTelefone: " + binding.telContainer.helperText
        }

        AlertDialog.Builder(context).setTitle("Cadastro Inválido").setMessage(message)
            .setPositiveButton("Ok") { _, _ ->
                //faça nada
            }.show()

    }

    private fun enviarForm() {

        val nome = binding.editNome.text.toString()
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val cpf = binding.editCPF.text.toString()
        val dataNasc = binding.editdataNasc.text.toString()
        val telefone = binding.editTel.text.toString()
        val genero = binding.spinnerGenero.isClickable


        var message = "Nome:" + binding.editNome.text
        message += "\nE-mail:" + binding.editEmail.text
        message += "\nSenha:" + binding.editSenha.text
        message += "\nCPF:" + binding.editCPF.text
        message += "\nData Nascimento:" + binding.editdataNasc.text
        message += "\nTelefone:" + binding.editTel.text


        AlertDialog.Builder(context).setTitle("Passo 1 Criado").setMessage(message)
            .setPositiveButton("Passo 1 Completado com Sucesso!") { _, _ ->
                binding.editNome.text = null
                binding.editEmail.text = null
                binding.editSenha.text = null
                binding.editCPF.text = null
                binding.editdataNasc.text = null
                binding.editTel.text = null

                binding.nomeContainer.helperText = getString(R.string.Required)
                binding.emailContainer.helperText = getString(R.string.Required)
                binding.senhaContainer.helperText = getString(R.string.Required)
                binding.cpfContainer.helperText = getString(R.string.Required)
                binding.dataNascContainer.helperText = ""
                binding.telContainer.helperText = getString(R.string.Required)

            }.show()

        val usuario = Usuario(
            0,
            cpf.toLong(),
            nome,
            dataNasc,
            genero,
            email,
            "endereco",
            senha,
            telefone.toLong()
        )
        mainViewModel.addUsuario(usuario)

        findNavController().navigate(R.id.action_cadastroUsuarioFragment_to_cadastroUsuario2Fragment)
    }


    private fun nomeCompletoFocusListener() {
        binding.editNome.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nomeContainer.helperText = validNomeCompleto()
            }
        }
    }

    private fun validNomeCompleto(): String? {
        val nomeText = binding.editNome.text.toString()
        if (nomeText == "" || nomeText.length < 3 || nomeText.length > 30) {
            return "Digite um nome válido!"
        }
        return null
    }

    private fun emailFocusListener() {
        binding.editEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.editEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "E-mail inválido!"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.editEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.senhaContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.editEmail.text.toString()
        if (passwordText.length < 6) {
            return "A senha deve conter no minimo 6 caracteres!"
        }

        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "A senha deve conter pelo menos uma letra minúscula!"
        }
        if (!passwordText.matches(".*[@#\$%^&+=.].*".toRegex())) {
            return "A senha deve conter pelo menos um caractere especial!"
        }
        return null
    }

    private fun cpfFocusListener() {
        binding.editCPF.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.cpfContainer.helperText = validCpf()
            }
        }
    }

    private fun validCpf(): String? {
        val cpf = binding.editCPF.text.toString()
        if (!cpf.matches(".*[0-9].*".toRegex())) {
            return "O numero do CPF só deve conter números!"
        }
        if (cpf.length < 8 || cpf.length > 11) {
            "CPF inválido!"
        }
        return null
    }

    private fun phoneFocusListener() {
        binding.editTel.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.telContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val editPhone = binding.editTel.text.toString()
        if (!editPhone.matches(".*[0-9].*".toRegex())) {
            return "O telefone só deve conter números!"
        }
        if (editPhone.length != 11) {
            return "Deve conter 11 numeros!"
        }
        return null
    }

    private fun dataNascFocusListener() {
        binding.editdataNasc.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.dataNascContainer.helperText = validDataNasc()
            }
        }
    }

    private fun validDataNasc(): String? {
        val nascText = binding.editdataNasc.text.toString()
        if (nascText.isBlank() || nascText.isEmpty()) {
            return "Digite uma data válida!"
        }
        return null

        return null
    }

}
