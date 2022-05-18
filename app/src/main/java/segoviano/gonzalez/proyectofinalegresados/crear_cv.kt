package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        var btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("usuarios")

        val etpuesto = findViewById<EditText>(R.id.txtPuesto)
        val etexperiencia = findViewById<EditText>(R.id.txtExperiencia)
        val etubicacion = findViewById<EditText>(R.id.txtPuestoUbicacion)
        val etrequisito = findViewById<EditText>(R.id.txtRequisitos)
        val ethorario = findViewById<EditText>(R.id.txtHorario)
        val etdescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val etsueldo = findViewById<EditText>(R.id.txtSueldo)

        btnRegresar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnFinalizar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}