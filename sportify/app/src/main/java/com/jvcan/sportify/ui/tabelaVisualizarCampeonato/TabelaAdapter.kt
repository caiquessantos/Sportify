package com.jvcan.sportify.ui.tabelaVisualizarCampeonato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.DB.model.FutStats
import com.jvcan.sportify.DB.model.PartidaStats
import com.jvcan.sportify.DB.model.Time
import com.jvcan.sportify.R

class TabelaAdapter : Adapter<TabelaAdapter.TabelaViewHolder>() {
  private var listaTabelas = mutableListOf<PartidaStats>()

  fun atualizarLista(lista : MutableList<PartidaStats>){
    listaTabelas = lista
  }

  inner class TabelaViewHolder(val itemView : View) : ViewHolder(itemView){
    val contexto = itemView.context
    val controllerTime = ControllerTime(contexto)
    val colocacao : TextView = itemView.findViewById(R.id.colocacao_time_tabela)
    val nome : TextView = itemView.findViewById(R.id.nome_time_tabela)
    val pontos : TextView = itemView.findViewById(R.id.pontos_time_tabela)
    val vitorias : TextView = itemView.findViewById(R.id.vitorias_time_tabela)
    val empates : TextView = itemView.findViewById(R.id.empates_time_tabela)
    val derrotas : TextView = itemView.findViewById(R.id.derrotas_time_tabela)
    val golsMarcados : TextView = itemView.findViewById(R.id.gols_marcados_time_tabela)
    val golsSofridos : TextView = itemView.findViewById(R.id.gols_sofridos_time_tabela)
    val saldoGols : TextView = itemView.findViewById(R.id.saldo_gols_time_tabela)
    fun bind(partidaStat : PartidaStats){
      colocacao.text = (listaTabelas.indexOf(partidaStat) + 1).toString()
      nome.text = controllerTime.read(partidaStat.idTime.toInt()).nome_time
      pontos.text = partidaStat.totalPontos.toString()
      vitorias.text = partidaStat.totalVitorias.toString()
      empates.text = partidaStat.totalEmpates.toString()
      derrotas.text = partidaStat.totalDerrotas.toString()
      golsMarcados.text = partidaStat.totalGolsFeitos.toString()
      golsSofridos.text = partidaStat.totalGolsSofridos.toString()
      saldoGols.text = partidaStat.saldoGols.toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabelaViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_tabela, parent, false)
    return TabelaViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: TabelaViewHolder, position: Int) {
    val tabela = listaTabelas[position]

    holder.bind(tabela)
  }

  override fun getItemCount(): Int {
    return listaTabelas.size
  }
}