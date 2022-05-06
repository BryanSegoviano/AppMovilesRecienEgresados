package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarContrasenia : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasenia)

        auth = Firebase.auth
        var btnVolver = findViewById<Button>(R.id.btnVolverMenu)
        val et_correo_olv: EditText = findViewById(R.id.txtCorreoRecuperacion)
        val btn_enviar : Button = findViewById(R.id.btnEnviarCorreo)

        btn_enviar.setOnClickListener {
            var correoOlvidado: String = et_correo_olv.text.toString().trim()

            auth.sendPasswordResetEmail(correoOlvidado)
                .addOnCompleteListener (this){ task->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Se ha enviado el correo de recuperación", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this, "No se envio el correo", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }
    }
}