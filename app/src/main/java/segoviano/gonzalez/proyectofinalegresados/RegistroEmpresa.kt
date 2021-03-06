package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistroEmpresa : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_empresa)

        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("empresas")

        var btnVolver = findViewById<Button>(R.id.btnVolver)
        var btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val etnombre: EditText = findViewById(R.id.txtNombreEmpresa)
        val etcorreo: EditText = findViewById(R.id.txtCorreoElectronico)
        val etcontrasenia: EditText = findViewById(R.id.txtContrasenia)
        val etconfirmarContrasenia: EditText = findViewById(R.id.txtConfirmarContrasenia)
        val etubicacion: EditText = findViewById(R.id.txtUbicacion)
        val etgiro: EditText = findViewById(R.id.txtGiro)

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            var nombre: String = etnombre.text.toString().trim()
            var correo: String = etcorreo.text.toString().trim()
            var contrasenia: String = etcontrasenia.text.toString().trim()
            var confirmarContrasenia: String = etconfirmarContrasenia.text.toString().trim()
            var ubicacion: String = etubicacion.text.toString().trim()
            var giro: String = etgiro.text.toString().trim()

            if(nombre.isNotEmpty() && correo.isNotEmpty() && contrasenia.isNotEmpty() &&
                confirmarContrasenia.isNotEmpty() && ubicacion.isNotEmpty() && giro.isNotEmpty()){
                if(contrasenia == confirmarContrasenia){
                    auth.createUserWithEmailAndPassword(correo, contrasenia)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val empresa = auth.currentUser
                                myRef.child(empresa?.uid.toString()).child("nombre").setValue(nombre)
                                myRef.child(empresa?.uid.toString()).child("ubicacion").setValue(ubicacion)
                                myRef.child(empresa?.uid.toString()).child("giro").setValue(giro)
                                Toast.makeText(this, "Se creo una cuenta exitosamente", Toast.LENGTH_SHORT).show()
                                val intent: Intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.w("mainactivity", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(this, "Las contrase??as no coinciden.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Debe ingresar un correo electr??nico y contrase??a.", Toast.LENGTH_SHORT).show()
            }

        }

    }
}