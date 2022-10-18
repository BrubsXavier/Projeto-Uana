package com.example.uana

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.uana.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.categoriaFragment, R.id.menuFragment)
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.visibility =
                if (destination.id == R.id.loginFragment2 ||
                    destination.id == R.id.apresentacaoFragment ||
                    destination.id == R.id.cadastroUsuario2Fragment ||
                    destination.id == R.id.cadastroUsuarioFragment ||
                    destination.id == R.id.homeFragment ||
                    destination.id == R.id.categoriaFragment ||
                    destination.id == R.id.menuFragment
                ) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }

        bottomNavigationView.setupWithNavController(navController)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility =
                if (destination.id == R.id.loginFragment2 ||
                    destination.id == R.id.apresentacaoFragment ||
                    destination.id == R.id.cadastroUsuario2Fragment ||
                    destination.id == R.id.cadastroUsuarioFragment ||
                    destination.id == R.id.recuperarContaFragment
                ) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_carrinho, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when (itemView) {
            R.id.carrinho -> Toast.makeText(applicationContext, "Botão clicado", Toast.LENGTH_SHORT)
                .show()
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}


