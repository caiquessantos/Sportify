package com.jvcan.sportify.ui.timesEditarCampeonato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.DB.model.JogaCampeonato
import com.jvcan.sportify.DB.model.Time
import com.jvcan.sportify.R

class TimeAdapter(private val cliqueTime : (Int) -> Unit) : Adapter<TimeAdapter.TimeViewHolder>() {
  private var listaTimes = mutableListOf<Time>()

  fun atualizarLista(lista : MutableList<Time>){
    listaTimes = lista
  }

  inner class TimeViewHolder(val itemView : View) : ViewHolder(itemView){
    val nomeTimeCard : TextView = itemView.findViewById(R.id.nome_time_adicionar_time)
    val botaoRemoverTime : ImageButton = itemView.findViewById(R.id.remover_time_adicionar_time)
    val cardTime : CardView = itemView.findViewById(R.id.cardview_time)
    fun bind(time : Time){
      nomeTimeCard.text = time.nome_time
      cardTime.setOnClickListener {
        cliqueTime(time.id_time)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_time, parent, false)
    return TimeViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
    val controleTime = ControllerTime(holder.botaoRemoverTime.context)
    val time = listaTimes[position]
    holder.botaoRemoverTime.setOnClickListener {
      controleTime.delete(time.id_time)
      val idRemovido = listaTimes.indexOf(time)
      listaTimes.remove(time)
      notifyItemRemoved(idRemovido)
    }

    holder.bind(time)
  }

  override fun getItemCount(): Int {
    return listaTimes.size
  }
}