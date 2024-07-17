package com.jvcan.sportify

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.databinding.ActivityGerenciarCampeonatoBinding

class GerenciarCampeonatoActivity : AppCompatActivity() {
  private val binding by lazy{
    ActivityGerenciarCampeonatoBinding.inflate(layoutInflater)
  }

  private lateinit var rvMeusCampeonatos: RecyclerView

  private val controleCampeonato = ControllerCampeonato(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.botaoVoltarGerenciarCampeonatos.setOnClickListener {
      finish()
    }

    var meusCampeonatos = controleCampeonato.getAllByUser(UsuarioLogado.usuarioLogado).toMutableList()

    rvMeusCampeonatos = binding.rvMeusCampeonatos
    val gerenciarCampeonatosAdapter = GerenciarCampeonatoAdapter{ idCampeonato ->
      val intent = Intent(this, EditarCampeonatoActivity::class.java)
      intent.putExtra("campeonatoEditando", idCampeonato)
      startActivity(intent)
    }
    gerenciarCampeonatosAdapter.atualizarLista(meusCampeonatos)
    rvMeusCampeonatos.adapter = gerenciarCampeonatosAdapter

    rvMeusCampeonatos.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
    itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
    rvMeusCampeonatos.addItemDecoration(itemDecoration)
  }
}