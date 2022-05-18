package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class publicar_empleo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publicar_empleo)

        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("empleos")

        val btnRegresar = findViewById<Button>(R.id.btnVuelve)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        val etpuesto = findViewById<EditText>(R.id.txtPuesto)
        val etexperiencia = findViewById<EditText>(R.id.txtExperiencia)
        val etubicacion = findViewById<EditText>(R.id.txtPuestoUbicacion)
        val etrequisito = findViewById<EditText>(R.id.txtRequisitos)
        val ethorario = findViewById<EditText>(R.id.txtHorario)
        val etdescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val etsueldo = findViewById<EditText>(R.id.txtSueldo)


        btnFinalizar.setOnClickListener {
            var puesto: String = etpuesto.text.toString().trim()
            var experiencia: String = etexperiencia.text.toString().trim()
            var ubicacion: String = etubicacion.text.toString().trim()
            var requisitos: String = etrequisito.text.toString().trim()
            var horario: String = ethorario.text.toString().trim()
            var descripcion: String = etdescripcion.text.toString().trim()
            var sueldo: String = etsueldo.text.toString().trim()

            if (puesto.isNotEmpty() && experiencia.isNotEmpty()
                && ubicacion.isNotEmpty() && requisitos.isNotEmpty()
                && descripcion.isNotEmpty()
                && sueldo.isNotEmpty()
            ) {
                myRef.child(puesto).child("puesto").setValue(puesto)
                myRef.child(puesto).child("experiencia").setValue(experiencia)
                myRef.child(puesto).child("ubicacion").setValue(ubicacion)
                myRef.child(puesto).child("requisitos").setValue(requisitos)
                myRef.child(puesto).child("horario").setValue(horario)
                myRef.child(puesto).child("descripcion").setValue(descripcion)
                myRef.child(puesto).child("sueldo").setValue(sueldo)
            }
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegresar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
