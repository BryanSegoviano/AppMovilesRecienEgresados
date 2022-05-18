package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import segoviano.gonzalez.proyectofinalegresados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        var btnCrearCV = findViewById<Button>(R.id.buttonCrearCV)
        var btnPublicarEmpleo = findViewById<Button>(R.id.buttonPublicarEmpleo)
        var btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        var txtUsuario = findViewById<TextView>(R.id.txtUsuario)

        auth = Firebase.auth
        val usuario = auth.currentUser

        val database = Firebase.database
        val myRef = database.getReference("usuarios")
        if(usuario != null){
        myRef.child(usuario?.uid.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var nombre = it.child("nombre").value
                val textoBienvenida = "¡Bienvenido usuario!\n$nombre"
                txtUsuario.text = textoBienvenida
                }
            }
        }




        btnCrearCV.setOnClickListener {
            val intent: Intent = Intent(this, crear_cv::class.java)
            startActivity(intent)
        }
        btnPublicarEmpleo.setOnClickListener {
            val intent: Intent = Intent(this, publicar_empleo::class.java)
            startActivity(intent)
        }
        btnCerrarSesion.setOnClickListener {
            auth.signOut()
            val intent: Intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }
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