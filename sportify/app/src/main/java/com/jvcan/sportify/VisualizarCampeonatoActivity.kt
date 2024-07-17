package com.jvcan.sportify

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jvcan.sportify.databinding.ActivityVisualizarCampeonatoBinding

class VisualizarCampeonatoActivity : AppCompatActivity() {

  private lateinit var binding: ActivityVisualizarCampeonatoBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityVisualizarCampeonatoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navView: BottomNavigationView = binding.navView

    val navController = findNavController(R.id.nav_host_fragment_activity_visualizar_campeonato)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_home_visualizar_campeonato, R.id.navigation_partidas_visualizar_campeonato, R.id.navigation_tabela_visualizar_campeonato
      )
    )
    //setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
  }
}