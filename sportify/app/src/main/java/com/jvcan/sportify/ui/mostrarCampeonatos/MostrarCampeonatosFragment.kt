package com.jvcan.sportify.ui.mostrarCampeonatos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.VisualizarCampeonatoActivity
import com.jvcan.sportify.databinding.FragmentMostrarCampeonatosBinding

class MostrarCampeonatosFragment : Fragment() {

  private var _binding: FragmentMostrarCampeonatosBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private lateinit var rvCampeonatoHome: RecyclerView
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMostrarCampeonatosBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }

  override fun onResume() {
    val controleCampeonato = ControllerCampeonato(activity)
    super.onResume()
    val campeonatos = controleCampeonato.all.toMutableList()
    rvCampeonatoHome = binding.rvCampeonatoHome
    val visualizarCampeonatoAdapter = CampeonatoAdapter{ idCampeonato ->
      val intent = Intent(this.context, VisualizarCampeonatoActivity::class.java)
      intent.putExtra("campeonatoVisualizado", idCampeonato)
      startActivity(intent)
    }
    visualizarCampeonatoAdapter.atualizarLista(campeonatos)
    rvCampeonatoHome.adapter =visualizarCampeonatoAdapter
    rvCampeonatoHome.layoutManager = GridLayoutManager(this.context,2)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}