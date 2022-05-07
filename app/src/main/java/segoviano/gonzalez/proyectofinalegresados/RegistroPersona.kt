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
import com.google.firebase.ktx.Firebase

class RegistroPersona : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_persona)

        auth = Firebase.auth
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val etcorreo: EditText = findViewById(R.id.txtCorreoElectronico)
        val etcontrasenia: EditText = findViewById(R.id.txtContrasenia)
        val etconfirmarContrasenia: EditText = findViewById(R.id.txtConfirmarContrasenia)

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            var correo: String = etcorreo.text.toString().trim()
            var contrasenia: String = etcontrasenia.text.toString().trim()
            var confirmarContrasenia: String = etconfirmarContrasenia.text.toString().trim()

            if(correo.isNotEmpty() && contrasenia.isNotEmpty() && confirmarContrasenia.isNotEmpty()) {
                if(contrasenia == confirmarContrasenia){
                auth.createUserWithEmailAndPassword(correo, contrasenia)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Se creo una cuenta exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent: Intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("mainactivity", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Debe ingresar un correo electrónico y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}