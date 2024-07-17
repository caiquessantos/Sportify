package com.jvcan.sportify

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.databinding.ActivityCriarCampeonatoBinding


class CriarCampeonatoActivity : AppCompatActivity() {

  private val controleCampeonato = ControllerCampeonato(this)

  private var permissaoGaleira = false
  private var permissaoMidiaExterna = false

  private val binding by lazy{
    ActivityCriarCampeonatoBinding.inflate(layoutInflater)
  }

  private var fotoCampeonatoURI = ""

  private val gerenciadorGaleira = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
    if(uri != null){
      fotoCampeonatoURI = uri.toString()
      binding.imagemCriarCampeonato.setImageURI(uri)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }


    val botaoVoltar = binding.botaoVoltarCriarCampeonato
    val botaoSalvar = binding.botaoSalvarCriarCampeonato
    val alterarImagem = binding.imagemCriarCampeonato

    botaoVoltar.setOnClickListener{
      finish()
    }

    alterarImagem.setOnClickListener {
      gerenciadorGaleira.launch("image/*")
    }

    botaoSalvar.setOnClickListener {

      val nomeCampeonato = binding.nomeCampeonatoCriarCampeonato.text.toString()
      val descricaoCampeonato = binding.descricaoCampeonatoCriarCampeonato.text.toString()
      val modalidadeCampeonato = if (binding.radioGroupModalidadeCriarCampeonato.checkedRadioButtonId == R.id.radio_button_futebol) "Futebol" else "Vôlei"
      val fotoCampeonato = if (fotoCampeonatoURI.equals("")) "padrao" else fotoCampeonatoURI
      controleCampeonato.create(UsuarioLogado.usuarioLogado, nomeCampeonato, modalidadeCampeonato, descricaoCampeonato, fotoCampeonato)
      finish()
    }
  }

  private fun solicitarPermissao(){
    permissaoGaleira = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
    permissaoMidiaExterna = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    val listaPermissoesNegadas = mutableListOf<String>()
    if(!permissaoGaleira){
      listaPermissoesNegadas.add(Manifest.permission.READ_MEDIA_IMAGES)
    }
    if(!permissaoMidiaExterna){
      listaPermissoesNegadas.add(Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    if(listaPermissoesNegadas.isNotEmpty()){

    }
  }
}