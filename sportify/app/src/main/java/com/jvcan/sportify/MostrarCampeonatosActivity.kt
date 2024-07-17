package com.jvcan.sportify

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.jvcan.sportify.DB.controller.ControllerUsuario
import com.jvcan.sportify.databinding.ActivityMostrarCampeonatosBinding

class MostrarCampeonatosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMostrarCampeonatosBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMostrarCampeonatosBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.appBarMostrarCampeonatos.toolbar)
    val drawerLayout: DrawerLayout = binding.drawerLayout
    val navView: NavigationView = binding.navView
    val navController = findNavController(R.id.nav_host_fragment_content_mostrar_campeonatos)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.nav_mostrar_campeonatos, R.id.nav_novo_campeonato, R.id.nav_contatos, R.id.nav_logout
      ), drawerLayout
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_mostrar_campeonatos)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
      R.id.nav_novo_campeonato -> {
        //finish()
        true
      }
      else -> false
    }
    return true
  }
}