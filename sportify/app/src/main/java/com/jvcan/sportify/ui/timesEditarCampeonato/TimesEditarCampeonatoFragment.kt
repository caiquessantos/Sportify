package com.jvcan.sportify.ui.timesEditarCampeonato

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.DB.controller.ControllerJogaCampeonato
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.R

import com.jvcan.sportify.databinding.FragmentTimesEditarCampeonatoBinding

class TimesEditarCampeonatoFragment : Fragment() {

  private var _binding: FragmentTimesEditarCampeonatoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private lateinit var rvTimes : RecyclerView
  private lateinit var imagemTime : ImageView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?

  ): View {
    val gerenciadorGaleria = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
      if(uri != null){
        imagemTime.setImageURI(uri)
      }
    }
    _binding = FragmentTimesEditarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.botaoVoltarTimesEditarCampeonato.setOnClickListener {
      activity?.finish()
    }

    val controleTime = ControllerTime(activity)
    val controleCampeonato = ControllerCampeonato(activity)
    val controleJogaCampeonato = ControllerJogaCampeonato(activity)

    val idCampeonatoAtual = requireActivity().intent.extras?.getInt("campeonatoEditando") ?: 0

    var times = controleCampeonato.getAllTimes(idCampeonatoAtual)

    rvTimes = binding.rvTimes
    val timesAdapter = TimeAdapter(){ idTime ->
      val intent = Intent(this.context, JogadoresActivity::class.java)
      intent.putExtra("idTime", idTime)
      startActivity(intent)
    }
    timesAdapter.atualizarLista(times)
    rvTimes.adapter = timesAdapter
    rvTimes.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    val itemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
    itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
    rvTimes.addItemDecoration(itemDecoration)


    binding.botaoAdicionarNovoTime.setOnClickListener {
      val dialogLayout = layoutInflater.inflate(R.layout.criar_time, null)
      val nomeTime = dialogLayout.findViewById<EditText>(R.id.edit_text_criar_time)
      imagemTime = dialogLayout.findViewById<ImageView>(R.id.imagem_criar_time)

      imagemTime.setOnClickListener {
        gerenciadorGaleria.launch("image/*")
      }

      with(MaterialAlertDialogBuilder(binding.botaoAdicionarNovoTime.context)){
        setTitle("Criar novo time:")
        setPositiveButton("Salvar"){dialog, which ->
          val idTime = controleTime.create(nomeTime.text.toString(), "padrao")
          controleJogaCampeonato.create(idTime.toInt(), idCampeonatoAtual)
          times = controleCampeonato.getAllTimes(idCampeonatoAtual).toMutableList()
          timesAdapter.atualizarLista(times)
          timesAdapter.notifyItemInserted(times.size-1)
        }
        setNegativeButton("Cancelar"){dialog, which ->

        }
        setView(dialogLayout)
        show()
      }
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}