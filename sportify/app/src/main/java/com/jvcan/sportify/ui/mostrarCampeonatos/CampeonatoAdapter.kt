package com.jvcan.sportify.ui.mostrarCampeonatos

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jvcan.sportify.DB.model.Campeonato
import com.jvcan.sportify.R

class CampeonatoAdapter(private val cliqueCampeonato : (Int) -> Unit) : Adapter<CampeonatoAdapter.CampeonatoViewHolder>(){

  private var listaCampeonatos = mutableListOf<Campeonato>()

  fun atualizarLista(lista : MutableList<Campeonato>){
    listaCampeonatos = lista
  }

  inner class CampeonatoViewHolder(val itemView : View) : ViewHolder(itemView){
    val imagemCampeonatoCard : ImageView = itemView.findViewById(R.id.imagem_campeonato_card)
    val tituloCampeonatoCard : TextView = itemView.findViewById(R.id.titulo_campeonato_card)
    val cardViewVisualizar : CardView = itemView.findViewById(R.id.card_view_campeonato_visualizar)
    fun bind(campeonato : Campeonato){
      tituloCampeonatoCard.text = campeonato.nome_campeonato
      if(campeonato.foto_campeonato.equals("padrao")){
        imagemCampeonatoCard.setImageResource(R.drawable.trofeu_padrao_campeonato)
      } else {
        imagemCampeonatoCard.setImageURI(Uri.parse(campeonato.foto_campeonato))
      }

      cardViewVisualizar.setOnClickListener{
        cliqueCampeonato(campeonato.id_campeonato)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampeonatoViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_campeonato, parent, false)
    return CampeonatoViewHolder(itemView)
  }

  override fun onBindViewHolder(campeonatoViewHolder: CampeonatoViewHolder, position: Int) {
    val campeonato = listaCampeonatos[position]
    campeonatoViewHolder.bind(campeonato)
  }

  override fun getItemCount(): Int {
    return listaCampeonatos.size
  }
}