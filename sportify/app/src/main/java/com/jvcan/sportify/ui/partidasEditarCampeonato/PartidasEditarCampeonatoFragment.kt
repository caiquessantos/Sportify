package com.jvcan.sportify.ui.partidasEditarCampeonato

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jvcan.sportify.DB.controller.Controller
import com.jvcan.sportify.DB.controller.ControllerCampeonato
import com.jvcan.sportify.DB.controller.ControllerTime
import com.jvcan.sportify.DB.model.Time
import com.jvcan.sportify.R
import com.jvcan.sportify.databinding.FragmentPartidasEditarCampeonatoBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import java.util.Locale

class PartidasEditarCampeonatoFragment : Fragment() {

  private var _binding: FragmentPartidasEditarCampeonatoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private lateinit var rvPartidas : RecyclerView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentPartidasEditarCampeonatoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.botaoVoltarPartidasEditarCampeonato.setOnClickListener {
      activity?.finish()
    }

    val controller = Controller(activity)
    val controllerCampeonato = ControllerCampeonato(activity)

    val idCampeonatoAtual = requireActivity().intent.extras?.getInt("campeonatoEditando") ?: 0

    var partidas = controller.readMatches(idCampeonatoAtual.toLong())

    rvPartidas = binding.rvPartidas
    val partidasAdapter = PartidaAdapter(){ partida ->
      val dialogLayout = layoutInflater.inflate(R.layout.criar_partida, null)
      val placarTime1 = dialogLayout.findViewById<NumberPicker>(R.id.criar_partida_time1_picker)
      placarTime1.minValue = 0
      placarTime1.maxValue = 99
      val placarTime2 = dialogLayout.findViewById<NumberPicker>(R.id.criar_partida_time2_picker)
      placarTime2.minValue = 0
      placarTime2.maxValue = 99

      val textoData = dialogLayout.findViewById<TextView>(R.id.texto_data_criar_partida)
      textoData.text = partida.data
      val botaoMudarData = dialogLayout.findViewById<ImageButton>(R.id.botao_alterar_data_criar_partida)
      botaoMudarData.setOnClickListener {
        val meuCalendario = Calendar.getInstance()
        val picaData = DatePickerDialog(botaoMudarData.context)
        picaData.setOnDateSetListener { view, year, month, dayOfMonth ->
          meuCalendario.set(Calendar.YEAR, year)
          meuCalendario.set(Calendar.MONTH, month)
          meuCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
          val myFormat = "dd/MM/yyyy"
          val sdf = SimpleDateFormat(myFormat, Locale.UK)
          textoData.text = sdf.format(meuCalendario.time)
        }
        picaData.show()
      }

      val textoHora = dialogLayout.findViewById<TextView>(R.id.texto_hora_criar_partida)
      textoHora.text = partida.horario
      val botaoMudarHora = dialogLayout.findViewById<ImageButton>(R.id.botao_alterar_hora_criar_partida)
      botaoMudarHora.setOnClickListener {
        val meuCalendario = Calendar.getInstance()
        val timePicker = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
          meuCalendario.set(Calendar.HOUR_OF_DAY, hourOfDay)
          meuCalendario.set(Calendar.MINUTE, minute)
          val myFormat = "HH:mm"
          val sdf = SimpleDateFormat(myFormat, Locale.UK)
          textoHora.text = sdf.format(meuCalendario.time)
        }
        TimePickerDialog(botaoMudarHora.context, timePicker, meuCalendario.get(Calendar.HOUR_OF_DAY), meuCalendario.get(Calendar.MINUTE), true).show()
      }

      with(MaterialAlertDialogBuilder(binding.botaoCriarNovaPartida.context)){
        setTitle("Editar partida:")
        setPositiveButton("Salvar"){dialog, which ->
          controller.updateMatch(partida.idPartida, partida.idcampeonato, partida.local, textoData.text.toString(), textoHora.text.toString(), placarTime1.value, placarTime2.value)
          partidas = controller.readMatches(idCampeonatoAtual.toLong())
        }
        setNegativeButton("Cancelar"){dialog, which ->

        }
        setView(dialogLayout)
        show()
      }
    }

    partidasAdapter.atualizarLista(partidas)
    rvPartidas.adapter = partidasAdapter
    rvPartidas.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    val itemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
    itemDecoration.setDrawable(resources.getDrawable(R.drawable.divisor_rv))
    rvPartidas.addItemDecoration(itemDecoration)

    binding.botaoCriarNovaPartida.setOnClickListener {
      val dialogLayout = layoutInflater.inflate(R.layout.criar_partida, null)
      var times = controllerCampeonato.getAllTimes(idCampeonatoAtual)
      var nomesTimes = arrayOfNulls<String>(times.size)
      for(i in 0..<times.size){
        nomesTimes[i] = times.get(i).nome_time
      }

      val nomeTime1 = dialogLayout.findViewById<NumberPicker>(R.id.criar_partida_time1_picker)
      nomeTime1.displayedValues = nomesTimes
      nomeTime1.minValue = 0
      nomeTime1.maxValue = nomesTimes.size-1

      val nomeTime2 = dialogLayout.findViewById<NumberPicker>(R.id.criar_partida_time2_picker)
      nomeTime2.displayedValues = nomesTimes
      nomeTime2.minValue = 0
      nomeTime2.maxValue = nomesTimes.size-1

      val textoData = dialogLayout.findViewById<TextView>(R.id.texto_data_criar_partida)
      val botaoMudarData = dialogLayout.findViewById<ImageButton>(R.id.botao_alterar_data_criar_partida)
      botaoMudarData.setOnClickListener {
        val meuCalendario = Calendar.getInstance()
        val picaData = DatePickerDialog(botaoMudarData.context)
        picaData.setOnDateSetListener { view, year, month, dayOfMonth ->
          meuCalendario.set(Calendar.YEAR, year)
          meuCalendario.set(Calendar.MONTH, month)
          meuCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
          val myFormat = "dd/MM/yyyy"
          val sdf = SimpleDateFormat(myFormat, Locale.UK)
          textoData.text = sdf.format(meuCalendario.time)
        }
        picaData.show()
      }

      val textoHora = dialogLayout.findViewById<TextView>(R.id.texto_hora_criar_partida)
      val botaoMudarHora = dialogLayout.findViewById<ImageButton>(R.id.botao_alterar_hora_criar_partida)
      botaoMudarHora.setOnClickListener {
        val meuCalendario = Calendar.getInstance()
        val timePicker = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
          meuCalendario.set(Calendar.HOUR_OF_DAY, hourOfDay)
          meuCalendario.set(Calendar.MINUTE, minute)
          val myFormat = "HH:mm"
          val sdf = SimpleDateFormat(myFormat, Locale.UK)
          textoHora.text = sdf.format(meuCalendario.time)
        }
        TimePickerDialog(botaoMudarHora.context, timePicker, meuCalendario.get(Calendar.HOUR_OF_DAY), meuCalendario.get(Calendar.MINUTE), true).show()
      }

      with(MaterialAlertDialogBuilder(binding.botaoCriarNovaPartida.context)){
        setTitle("Criar nova partida:")
        setPositiveButton("Salvar"){dialog, which ->
          val idParticaCriada = controller.createMatch(idCampeonatoAtual.toLong(), "Estadio", textoData.text.toString(), textoHora.text.toString(), -1, -1)
          var idTime1 : Int = 0
          var idTime2 : Int = 0
          println(nomeTime1.value.toString())
          println(nomeTime2.value.toString())
          for(time : Time in times){
            if(time.nome_time.equals(nomesTimes[(nomeTime1.value)])){
              idTime1 = time.id_time
            }
            if(time.nome_time.equals(nomesTimes[(nomeTime2.value)])){
              idTime2 = time.id_time
            }
          }
          controller.createPlayMatch(idTime1.toLong(),idParticaCriada, true)
          controller.createPlayMatch(idTime2.toLong(),idParticaCriada, false)

          partidas = controller.readMatches(idCampeonatoAtual.toLong())
          partidasAdapter.atualizarLista(partidas)
          partidasAdapter.notifyItemInserted(partidas.size-1)
        }
        setNegativeButton("Cancelar"){dialog, which ->

        }
        setView(dialogLayout)
        show()
      }
    }


    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}