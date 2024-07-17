package com.jvcan.sportify.ui.timesEditarCampeonato

import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jvcan.sportify.DB.controller.ControllerJogador
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.R
import com.jvcan.sportify.databinding.ActivityJogadoresBinding

class JogadoresActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityJogadoresBinding.inflate(layoutInflater)
    }

    private val controleTime = ControllerTime(this)
    private lateinit var rvJogadores : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras

        binding.botaoVoltarJogadores.setOnClickListener {
            finish()
        }

        val controleJogador = ControllerJogador(this)

        binding.textJogadoresNomeTime.text = "Jogadores ${controleTime.read(bundle!!.getInt("idTime")).nome_time}"

        var jogadores = controleJogador.getAllByTimeId(bundle!!.getInt("idTime"))

        rvJogadores = binding.rvJogadores
        val jogadoresAdapter = JogadorAdapter()
        jogadoresAdapter.atualizarLista(jogadores)
        rvJogadores.adapter = jogadoresAdapter
        rvJogadores.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
        rvJogadores.addItemDecoration(itemDecoration)


        binding.botaoAdicionarNovoJogador.setOnClickListener {
            val dialogLayout = layoutInflater.inflate(R.layout.criar_jogador, null)
            val nomeJogador = dialogLayout.findViewById<EditText>(R.id.criar_jogador_nome)
            val numeroJogador = dialogLayout.findViewById<NumberPicker>(R.id.escolhedor_numero_jogador)
            numeroJogador.minValue = 0
            numeroJogador.maxValue = 99

            with(MaterialAlertDialogBuilder(binding.botaoAdicionarNovoJogador.context)){
                setTitle("Criar novo jogador:")
                setPositiveButton("Salvar"){dialog, which ->
                    controleJogador.create(bundle!!.getInt("idTime"), nomeJogador.text.toString(), numeroJogador.value.toString())
                    jogadores = controleJogador.getAllByTimeId(bundle!!.getInt("idTime"))
                    jogadoresAdapter.atualizarLista(jogadores)
                    jogadoresAdapter.notifyItemInserted(jogadores.size-1)
                }
                setNegativeButton("Cancelar"){dialog, which ->

                }
                setView(dialogLayout)
                show()
            }
        }
    }
}