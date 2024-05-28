package com.pdm115gt3g2.pedidosapp

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.pdm115gt3g2.pedidosapp.databinding.ActivityMainBinding
import com.pdm115gt3g2.pedidosapp.db.LlenarBdWorker
import com.pdm115gt3g2.pedidosapp.db.repositories.ItemRepositorio
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuDetalleRepository
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository
import com.pdm115gt3g2.pedidosapp.db.repositories.TipoItemRepository

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //repositorios para interactuar con la BD
    private lateinit var tipoItemRepository: TipoItemRepository
    private lateinit var itemRepositorio: ItemRepositorio
    private lateinit var menuRepository: MenuRepository
    private lateinit var menuDetalleRepository: MenuDetalleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio, R.id.nav_menus
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //para verificar que la base de datos se llene solo una vez
        //se usa un WORKER para ejecutar el llenado en segundo plano
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isTestDataInserted = sharedPreferences.getBoolean("is_test_data_inserted", false)
        if (!isTestDataInserted) {
            val workRequest = OneTimeWorkRequestBuilder<LlenarBdWorker>().build()
            WorkManager.getInstance(this).enqueue(workRequest)
            sharedPreferences.edit().putBoolean("is_test_data_inserted", true).apply()
        }

    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}