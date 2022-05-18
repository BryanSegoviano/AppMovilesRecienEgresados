package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class crear_cv : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv)

        var btnRegresar = findViewById<Button>(R.id.btnVuelve)
        var btnFinalizar = findViewById<Button>(R.id.btnFinalizarCv)

        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("usuarios")

        val etdireccion = findViewById<EditText>(R.id.txtDireccion)
        val etciudad = findViewById<EditText>(R.id.txtCiudad)
        val ettelefono = findViewById<EditText>(R.id.txtTelefono)
        val etgrado = findViewById<EditText>(R.id.txtGrado)
        val etestudio = findViewById<EditText>(R.id.txtEstudio)
        val etexperiencia = findViewById<EditText>(R.id.txtExperiencia)
        val ethabilidades = findViewById<EditText>(R.id.txtHabilidades)
        val etidiomas = findViewById<EditText>(R.id.txtIdiomas)

        btnRegresar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val usuario = auth.currentUser

        btnFinalizar.setOnClickListener {
            var direccion: String = etdireccion.text.toString().trim()
            var ciudad: String = etciudad.text.toString().trim()
            var telefono: String = ettelefono.text.toString().trim()
            var grado: String = etgrado.text.toString().trim()
            var estudio: String = etestudio.text.toString().trim()
            var experiencia: String = etexperiencia.text.toString().trim()
            var habilidades: String = ethabilidades.text.toString().trim()
            var idiomas: String = etidiomas.text.toString().trim()

            if (direccion.isNotEmpty() && ciudad.isNotEmpty()
                && telefono.isNotEmpty() && grado.isNotEmpty()
                && estudio.isNotEmpty() && experiencia.isNotEmpty()
                && habilidades.isNotEmpty() && idiomas.isNotEmpty()
            ) {
                myRef.child(usuario?.uid.toString()).child("curriculum").child("direccion").setValue(direccion)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("ciudad").setValue(ciudad)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("telefono").setValue(telefono)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("grado").setValue(grado)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("estudio").setValue(estudio)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("experiencia").setValue(experiencia)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("habilidades").setValue(habilidades)
                myRef.child(usuario?.uid.toString()).child("curriculum").child("idiomas").setValue(idiomas)
            }
            Toast.makeText(this, "¡CURRICULUM GUARDADO CORRECTAMENTE!", Toast.LENGTH_LONG).show()
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}