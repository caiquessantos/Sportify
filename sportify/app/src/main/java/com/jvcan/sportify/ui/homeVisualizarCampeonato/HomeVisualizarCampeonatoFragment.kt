package com.jvcan.sportify.ui.homeVisualizarCampeonato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.databinding.FragmentHomeVisualizarCampeonatoBinding

class HomeVisualizarCampeonatoFragment : Fragment() {

  private var _binding: FragmentHomeVisualizarCampeonatoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentHomeVisualizarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.botaoVoltarVisualizarCampeonatoHome.setOnClickListener {
      activity?.finish()
    }

    val controllerCampeonato = ControllerCampeonato(activity)

    val idCampeonatoVisualizado = requireActivity().intent.extras!!.getInt("campeonatoVisualizado")
    val campeonatoVisualizado = controllerCampeonato.read(idCampeonatoVisualizado)

    binding.tituloCampeonatoVisualizarCampeonatoHome.text = campeonatoVisualizado.nome_campeonato
    binding.descricaoCampeonatoVisualizarCampeonatoHome.setText(campeonatoVisualizado.descricao)
    binding.modalidadeCampeonatoVisualizarCampeonatoHome.text = campeonatoVisualizado.modalidade

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}