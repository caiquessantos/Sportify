package com.jvcan.sportify.ui.homeEditarCampeonato

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.R
import com.jvcan.sportify.databinding.FragmentHomeEditarCampeonatoBinding

class HomeEditarCampeonatoFragment : Fragment() {

  private var _binding: FragmentHomeEditarCampeonatoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentHomeEditarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val controleCampeonato = ControllerCampeonato(activity)

    val idCampeonatoAtual = requireActivity().intent.extras?.getInt("campeonatoEditando") ?: 0
    val campeonatoAtual = controleCampeonato.read(idCampeonatoAtual)

    if (campeonatoAtual.foto_campeonato.equals("padrao")){
      binding.imagemEditarCampeonato.setImageResource(R.drawable.trofeu_padrao_campeonato)
    } else {
      binding.imagemEditarCampeonato.setImageURI(Uri.parse(campeonatoAtual.foto_campeonato))
    }

    binding.textoNomeEditarCampeonato.text = campeonatoAtual.nome_campeonato

    binding.textoModalidadeEditarCampeonato.text = campeonatoAtual.modalidade

    binding.textoDescricaoEditarCampeonato.setText(campeonatoAtual.descricao)


    binding.botaoVoltarEditarCampeonatos.setOnClickListener {
      activity?.finish()
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}