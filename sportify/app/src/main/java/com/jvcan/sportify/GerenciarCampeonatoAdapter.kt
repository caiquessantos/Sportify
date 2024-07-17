package com.jvcan.sportify

import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.DB.model.Campeonato


class GerenciarCampeonatoAdapter(private val cliqueCampeonato : (Int)->Unit) : Adapter<GerenciarCampeonatoAdapter.GerenciarCampeonatoViewHolder>() {

  private var listaMeusCampeonatos = mutableListOf<Campeonato>()

  fun atualizarLista(lista : MutableList<Campeonato>){
    listaMeusCampeonatos = lista
  }

  inner class GerenciarCampeonatoViewHolder(val itemView: View) : ViewHolder(itemView){
    val tituloMeuCampeonatoCard : TextView = itemView.findViewById(R.id.nome_meu_campeonato_text)
    val modalidadeMeuCampeonato : TextView = itemView.findViewById(R.id.modalidade_campeonato_text)
    val fotoMeuCampeonato : ImageView = itemView.findViewById(R.id.campeonato_gerenciar_image)
    val botaoRemoverCampeonato : ImageButton = itemView.findViewById(R.id.botao_remover_campeonato)
    val cardViewGerenciar : CardView = itemView.findViewById(R.id.cardview_campeonato_gerenciar)
    fun bind(campeonato : Campeonato){
      tituloMeuCampeonatoCard.text = campeonato.nome_campeonato
      modalidadeMeuCampeonato.text = campeonato.modalidade
      if(campeonato.foto_campeonato.equals("padrao")){
        fotoMeuCampeonato.setImageResource(R.drawable.trofeu_padrao_campeonato)
      } else {
        fotoMeuCampeonato.setImageURI(Uri.parse(campeonato.foto_campeonato))
      }
      cardViewGerenciar.setOnClickListener{
        cliqueCampeonato(campeonato.id_campeonato)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GerenciarCampeonatoViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_campeonato_gerenciar, parent, false)
    return GerenciarCampeonatoViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: GerenciarCampeonatoViewHolder, position: Int) {
    val controleCampeonato = ControllerCampeonato(holder.botaoRemoverCampeonato.context)

    val campeonato = listaMeusCampeonatos[position]

    holder.botaoRemoverCampeonato.setOnClickListener {
      val idRemovido = listaMeusCampeonatos.indexOf(campeonato)
      MaterialAlertDialogBuilder(holder.botaoRemoverCampeonato.context)
        .setTitle("Confirmar exclusão")
        .setMessage("Deseja realmente excluir o campeonato ${campeonato.nome_campeonato}?")
        .setCancelable(true)
        .setPositiveButton("Sim",DialogInterface.OnClickListener { dialog, which ->
          controleCampeonato.delete(campeonato.id_campeonato)
          listaMeusCampeonatos.remove(campeonato)
          notifyItemRemoved(idRemovido)
        })
        .setNegativeButton("Não", DialogInterface.OnClickListener { dialog, which ->
          dialog.cancel()
        })
        .show()
    }

    holder.bind(campeonato)
  }

  override fun getItemCount(): Int {
    return listaMeusCampeonatos.size
  }
}