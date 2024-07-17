package com.jvcan.sportify

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.jvcan.sportify.DB.controller.ControllerUsuario
import com.jvcan.sportify.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

  private val binding by lazy{
    ActivityLoginBinding.inflate(layoutInflater)
  }

  private val controleUsuario = ControllerUsuario(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContentView(binding.root)
    var botaoIrParaCadastro = binding.buttonIrParaCadastro
    var botaoFazerLogin = binding.buttonFazerLogin

    botaoIrParaCadastro.setOnClickListener {
      startActivity(Intent(this, CadastroActivity::class.java))
    }

    botaoFazerLogin.setOnClickListener{
      if(controleUsuario.login(binding.emailEditText.text.toString().lowercase(), binding.senhaEditText.text.toString())){
        UsuarioLogado.usuarioLogado = binding.emailEditText.text.toString().lowercase()
        val intent = Intent(this, MostrarCampeonatosActivity::class.java)
        intent.putExtra("usuarioLogado", binding.emailEditText.text.toString())

        startActivity(intent)
      } else {
        binding.emailTextInputLayout.error = "Email ou senha incorretos!"
      }
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.emailEditText.addTextChangedListener {
      binding.emailTextInputLayout.helperText = validEmail()
    }
  }

  private fun validEmail():String?{
    val textoEmail = binding.emailEditText.text.toString()
    if(!Patterns.EMAIL_ADDRESS.matcher(textoEmail).matches()){
      binding.emailTextInputLayout.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.error)))
      return "E-mail inválido"
    }
    return null
  }
}