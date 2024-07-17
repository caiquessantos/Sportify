package com.jvcan.sportify.ui.partidasVisualizarCampeonato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvcan.sportify.DB.controller.Controller
import com.jvcan.sportify.R
import com.jvcan.sportify.databinding.FragmentPartidasVisualizarCampeonatoBinding
import com.jvcan.sportify.ui.partidasEditarCampeonato.PartidaAdapter

class PartidasVisualizarCampeonatoFragment : Fragment() {

  private var _binding: FragmentPartidasVisualizarCampeonatoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentPartidasVisualizarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.botaoVoltarPartidasVisualizarCampeonato.setOnClickListener {
      activity?.finish()
    }

    val controller = Controller(activity)

    val idCampeonato = requireActivity().intent.extras!!.getInt("campeonatoVisualizado")

    var partidas = controller.readMatches(idCampeonato.toLong())
    val rvPartidasVisualizar = binding.rvPartidasVisualizar

    val adapterPartidasVisualizar = PartidaAdapter(){}
    adapterPartidasVisualizar.atualizarLista(partidas)
    rvPartidasVisualizar.adapter = adapterPartidasVisualizar
    rvPartidasVisualizar.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    val itemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
    itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
    rvPartidasVisualizar.addItemDecoration(itemDecoration)

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}