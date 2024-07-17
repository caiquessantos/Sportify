package com.jvcan.sportify.ui.tabelaVisualizarCampeonato

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
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.DB.model.FutStats
import com.jvcan.sportify.DB.model.PartidaStats
import com.jvcan.sportify.DB.model.Time
import com.jvcan.sportify.R
import com.jvcan.sportify.databinding.FragmentTabelaVisualizarCampeonatoBinding

class TabelaVisualizarCampeonatoFragment : Fragment() {

  private var _binding: FragmentTabelaVisualizarCampeonatoBinding? = null
  private lateinit var rvTabela : RecyclerView

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentTabelaVisualizarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.botaoVoltarTabelaVisualizarCampeonato.setOnClickListener{
      activity?.finish()
    }
    var listaTabelas = mutableListOf<PartidaStats>()
    val controller = Controller(activity)
    val controllerCampeonato = ControllerCampeonato(activity)
    val idCampeonato = requireActivity().intent.extras!!.getInt("campeonatoVisualizado")
    val listaTimes = controllerCampeonato.getAllTimes(idCampeonato)
    for (time : Time in listaTimes){
      val listaCasa = controller.getHomeMatches(idCampeonato.toLong(), time.id_time.toLong())
      val listaFora = controller.getAwayMatches(idCampeonato.toLong(), time.id_time.toLong())
      var totalPartidas = listaFora.totalPartidas + listaCasa.totalPartidas
      var totalVitorias = listaFora.vitorias + listaCasa.vitorias
      var totalEmpates = listaFora.empates + listaCasa.empates
      var totalDerrotas = listaFora.derrotas + listaCasa.derrotas
      var totalGF = listaFora.totalGolsFeitos + listaCasa.totalGolsFeitos
      var totalGS = listaFora.totalGolsTomados + listaCasa.totalGolsTomados
      var totalSG = totalGF - totalGS
      var totalPontos = (3 * totalVitorias) + totalEmpates
      listaTabelas.add(PartidaStats(time.id_time.toLong(), totalPartidas, totalVitorias, totalEmpates, totalDerrotas, totalGF, totalGS, totalSG, totalPontos))
    }
    listaTabelas = listaTabelas.sortedWith(compareBy({it.totalPontos}, {it.saldoGols})).asReversed().toMutableList()
    rvTabela = binding.rvTabela
    val adapterTabela = TabelaAdapter()
    adapterTabela.atualizarLista(listaTabelas)
    rvTabela.adapter = adapterTabela
    rvTabela.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    val itemDecoration = DividerItemDecoration(activity, RecyclerView.VERTICAL)
    itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
    rvTabela.addItemDecoration(itemDecoration)

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}