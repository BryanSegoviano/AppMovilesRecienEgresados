package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import segoviano.gonzalez.proyectofinalegresados.databinding.ActivityMainBinding
import segoviano.gonzalez.proyectofinalegresados.ui.Empleo

class MainActivity : AppCompatActivity() {

    companion object {
        var listaEmpleos: ArrayList<Empleo> = ArrayList()
        var listaEmpleosPostulados: ArrayList<Empleo> = ArrayList()
    }

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
        this.llenadoEmpleos(database)

        if (usuario != null) {
            myRef.child(usuario?.uid.toString()).get().addOnSuccessListener {
                if (it.exists()) {
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

    fun llenadoEmpleos(database: FirebaseDatabase) {
        listaEmpleos.clear()
        listaEmpleosPostulados.clear()
        val empleosDB = database.getReference("empleos")
        val usuariosBD = database.getReference("usuarios")

        empleosDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    empleosDB.child(postSnapshot.key.toString()).get().addOnSuccessListener {
                        if (it.exists()) {
                            var puesto = it.child("puesto").value.toString()
                            var experiencia = it.child("experiencia").value.toString()
                            var ubicacion = it.child("ubicacion").value.toString()
                            var requisitos = it.child("requisitos").value.toString()
                            var horario = it.child("horario").value.toString()
                            var descripcion = it.child("descripcion").value.toString()
                            var sueldo = it.child("sueldo").value.toString()
                            var empresa = empleosDB.child("google").key.toString()

                            val empleo1 = Empleo(
                                1,
                                R.drawable.iconozerexp,
                                puesto,
                                experiencia,
                                ubicacion,
                                requisitos,
                                horario,
                                descripcion,
                                sueldo.toFloat(),
                                empresa
                            )
                            listaEmpleos.add(empleo1)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.v("TAG", "loadPost:onCancelled", databaseError.toException())
            }
        })

        val usuario = auth.currentUser

        usuariosBD.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    usuariosBD.child(usuario?.uid.toString()).get().addOnSuccessListener {
                        for (empleo in listaEmpleos) {
                            //si es diferente de null que lo guarde en la lista
                            val postulacion = it.child("postulaciones").child(empleo.puesto).value.toString()
                            if (postulacion != "null") {
                                listaEmpleosPostulados.add(empleo)
                            }
                        }
                    }
                }//
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.v("TAG", "errrrrrrrrrrrrorrrrrrrrr")
            }
        })
    }
}