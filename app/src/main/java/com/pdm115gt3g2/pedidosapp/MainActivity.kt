package com.pdm115gt3g2.pedidosapp

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.pdm115gt3g2.pedidosapp.databinding.ActivityMainBinding
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import com.pdm115gt3g2.pedidosapp.db.repositories.TipoItemRepository

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //repositorio para usar la tabla de TipoItems
    private lateinit var tipoItemRepository: TipoItemRepository

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

        //iniciando base de datos
        val db = PedidosAppDataBase.getDatabase(this)
        //accediendo al Dao
        val tipoItemDao = db.TipoItemDao()

        //iniciando repositorio de db
        tipoItemRepository = TipoItemRepository(tipoItemDao)

        //insertando tipos de items
        val tipo1 = TipoItem(nombreTipo = "hamburgesas")
        val tipo2 = TipoItem(nombreTipo = "pizzas")
        val tipo3 = TipoItem(nombreTipo = "bebidas")
        tipoItemRepository.insertar(tipo1)
        tipoItemRepository.insertar(tipo2)
        tipoItemRepository.insertar(tipo3)

        //IMPORTANTE: cerrar base de datos
        db.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}