package com.example.uana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uana.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.buttonEntrar.setOnClickListener {

            if (textEmailAddress.text.toString()
                    .equals("admin@gmail.com") && textPassword.text.toString()
                    .equals("admin")
            ) {

                Toast.makeText(context, "Seja Bem-Vindo!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)

            } else {

                Toast.makeText(
                    context,
                    "Combinação de usuário e senha inválida!",
                    Toast.LENGTH_SHORT
                ).show()


                //acessarConta()

            }
        }

            binding.buttonNovaConta.setOnClickListener {

                findNavController().navigate(R.id.action_loginFragment2_to_cadastroUsuarioFragment)

            }

            binding.textEsqueciSenha.setOnClickListener {

                findNavController().navigate((R.id.action_loginFragment2_to_recuperarContaFragment2))
            }

            return binding.root

        }

        /*private fun validarCampos(email: String, senha: String): Boolean {

            return !(email == "" || email.length <= 3) &&
                    (senha == "" || senha.length <= 5)
        }

        private fun acessarConta() {

            val email = binding.textEmailAddress.text.toString()
            val senha = binding.textPassword.text.toString()

            if (validarCampos(email, senha)) {

                mainViewModel.consultaUsuarioEmail(email)
                mainViewModel.consultaUsuarioSenha(senha)

                mainViewModel.usuarioLog.observe(viewLifecycleOwner) { response ->
                    if (response.body() != null) {
                        if (senha == mainViewModel.usuarioLog.value?.body()?.senha.toString()) {
                            Toast.makeText(
                                context,
                                "Seja Bem-Vindo!",
                                Toast.LENGTH_SHORT
                            ).show()

                            findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "Combinação de usuário e senha inválida!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            } else {
                Toast.makeText(
                    context,
                    "Preencha todos os campos!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }*/
    }
