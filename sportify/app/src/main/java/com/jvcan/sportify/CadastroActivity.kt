package com.jvcan.sportify

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.jvcan.sportify.DB.controller.ControllerUsuario
import com.jvcan.sportify.databinding.ActivityCadastroBinding
import java.util.Locale

class CadastroActivity : AppCompatActivity() {

  private val binding by lazy{
    ActivityCadastroBinding.inflate(layoutInflater)
  }

  private val controleUsuario = ControllerUsuario(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)

    var botaoVoltar = binding.botaoVoltar
    var botaoIrParaLogin = binding.botaoIrParaLogin

    botaoVoltar.setOnClickListener {
      finish()
    }

    botaoIrParaLogin.setOnClickListener {
      finish()
    }

    binding.emailcadastroInputText.addTextChangedListener {
      binding.emailcadastroTextInputLayout.helperText = validEmail()
    }

    binding.senhacadastroInputText.addTextChangedListener {
      binding.senhacadastroTextInputLayout.helperText = validSenha()
    }

    binding.botaoCadastrar.setOnClickListener{
      if(binding.nomeInputText.text.toString().isEmpty()){
        binding.nomeTextInputLayout.error = "Campo Requerido!"
      } else if(controleUsuario.testaUsuario(binding.emailcadastroInputText.text.toString())) {
        binding.emailcadastroTextInputLayout.error = "Email já cadastrado!"
      } else if(binding.emailcadastroInputText.text.toString().isEmpty()){
        binding.emailcadastroTextInputLayout.error = "Campo Requerido!"
      } else if(binding.senhacadastroInputText.text.toString().isEmpty()){
        binding.senhacadastroTextInputLayout.error = "Campo Requerido!"
      } else if(binding.confirmarsenhaInputText.text.toString().isEmpty()){
        binding.confimarsenhaTextInputLayout.error = "Campo Requerido!"
      } else if(binding.emailcadastroTextInputLayout.helperText != null){
        binding.emailcadastroTextInputLayout.error = "E-mail inválido!"
      } else if(binding.senhacadastroTextInputLayout.helperText != null){
        binding.senhacadastroTextInputLayout.error = "Senha inválida!"
      } else if(!binding.senhacadastroInputText.text.toString().equals(binding.confirmarsenhaInputText.text.toString())){
        binding.confimarsenhaTextInputLayout.error = "Senhas diferentes!"
      } else {
        controleUsuario.create(binding.nomeInputText.text.toString().lowercase(), binding.emailcadastroInputText.text.toString().lowercase(), binding.senhacadastroInputText.text.toString())
        finish()
      }
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
  }

  private fun validEmail():String?{
    val textoEmail = binding.emailcadastroInputText.text.toString()
    if(!Patterns.EMAIL_ADDRESS.matcher(textoEmail).matches()){
      binding.emailcadastroTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "E-mail inválido"
    }
    return null
  }

  private fun validSenha():String?{
    val textoSenha = binding.senhacadastroInputText.text.toString()
    if(textoSenha.length < 8){
      binding.senhacadastroTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "Mínimo 8 caractéres para senha"
    }
    if(!textoSenha.matches(".*[A-Z].*".toRegex())){
      binding.senhacadastroTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "Precisa conter uma letra maiúscula"
    }
    if(!textoSenha.matches(".*[a-z].*".toRegex())){
      binding.senhacadastroTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "Precisa conter uma letra minúsucla"
    }
    if(!textoSenha.matches(".*[0-9].*".toRegex())){
      binding.senhacadastroTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "Precisa conter um número"
    }
    return null
  }
}