package com.jvcan.sportify.ui.partidasEditarCampeonato

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jvcan.sportify.DB.controller.Controller
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.DB.model.Partida
import com.jvcan.sportify.R
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

class PartidaAdapter(private val clique : (Partida) -> Unit) : Adapter<PartidaAdapter.PartidaViewHolder>() {

  private var listaPartidas = mutableListOf<Partida>()

  fun atualizarLista(lista : MutableList<Partida>){
    listaPartidas = lista
  }


  inner class PartidaViewHolder(val itemView : View) : ViewHolder(itemView){
    val contexto = itemView.context
    val controller = Controller(contexto)
    val controllerTime = ControllerTime(contexto)
    val cardPartida : CardView = itemView.findViewById(R.id.card_view_partida)
    val nomeTime1 : TextView = itemView.findViewById(R.id.texto_nome_partida_time1)
    val nomeTime2 : TextView = itemView.findViewById(R.id.texto_nome_partida_time2)
    val escudoTime1 : ImageView = itemView.findViewById(R.id.imagem_partida_time1)
    val escudoTime2 : ImageView = itemView.findViewById(R.id.imagem_partida_time2)
    val placarTime1 : TextView = itemView.findViewById(R.id.texto_placar_partida_time1)
    val placarTime2 : TextView = itemView.findViewById(R.id.texto_placar_partida_time2)
    val dataPartida : TextView = itemView.findViewById(R.id.texto_data_partida)
    val horarioPartida : TextView = itemView.findViewById(R.id.texto_hora_partida)
    fun bind(partida : Partida){

      cardPartida.setOnClickListener {
        clique(partida)
        atualizarLista(controller.readMatches(partida.idcampeonato))
        notifyDataSetChanged()
      }

      val times = controller.readPlayMatch(partida.idPartida)
      val idTime1 : Int
      val idTime2 : Int
      if(times[0].isMandante){
        idTime1 = times[0].idTime.toInt()
        idTime2 = times[1].idTime.toInt()
      } else {
        idTime2 = times[0].idTime.toInt()
        idTime1 = times[1].idTime.toInt()
      }
      nomeTime1.text = controllerTime.read(idTime1).nome_time
      nomeTime2.text = controllerTime.read(idTime2).nome_time
      escudoTime1.setImageResource(R.drawable.escudo_padrao_time)
      escudoTime2.setImageResource(R.drawable.escudo_padrao_time)
      placarTime1.text = if(partida.placarTime1 == -1) "-" else partida.placarTime1.toString()
      placarTime2.text = if(partida.placarTime2 == -1) "-" else partida.placarTime2.toString()
      dataPartida.text = partida.data
      horarioPartida.text = partida.horario
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidaViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_cardview_partida, parent, false)
    return PartidaViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: PartidaViewHolder, position: Int) {
    val partida = listaPartidas[position]

    holder.bind(partida)
  }

  override fun getItemCount(): Int {
    return listaPartidas.size
  }
}