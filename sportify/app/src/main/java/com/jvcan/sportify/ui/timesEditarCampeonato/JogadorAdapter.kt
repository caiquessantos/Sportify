package com.jvcan.sportify.ui.timesEditarCampeonato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jvcan.sportify.DB.controller.ControllerJogador
import com.jvcan.sportify.DB.model.Jogador
import com.jvcan.sportify.R

class JogadorAdapter() : Adapter<JogadorAdapter.JogadorViewHolder>() {
  private var listaJogadores = mutableListOf<Jogador>()

  fun atualizarLista(lista : MutableList<Jogador>){
    listaJogadores = lista
  }

  inner class JogadorViewHolder(val itemView : View) : ViewHolder(itemView){
    val botaoRemoverJogador : ImageButton = itemView.findViewById(R.id.botao_excluir_jogador)
    val numeroJogador : TextView = itemView.findViewById(R.id.text_numero_jogador)
    val nomeJogador : TextView = itemView.findViewById(R.id.text_nome_jogador)

    fun bind(jogador : Jogador){
      numeroJogador.text = jogador.numero
      nomeJogador.text = jogador.nome_jogador
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogadorViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_jogador, parent, false)
    return JogadorViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: JogadorViewHolder, position: Int) {
    val controleJogador = ControllerJogador(holder.botaoRemoverJogador.context)

    val jogador = listaJogadores[position]

    holder.botaoRemoverJogador.setOnClickListener {
      controleJogador.delete(jogador.id_jogador)
      val idRemovido = listaJogadores.indexOf(jogador)
      listaJogadores.remove(jogador)
      notifyItemRemoved(idRemovido)
    }

    holder.bind(jogador)
  }

  override fun getItemCount(): Int {
    return listaJogadores.size
  }
}