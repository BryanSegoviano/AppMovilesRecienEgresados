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

class InicioSesion : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        auth = Firebase.auth
        val et_correo : EditText = findViewById(R.id.txtNombreUsuario)
        val et_contra : EditText = findViewById(R.id.txtContrasenia)
        val btnIniciarSesion : Button = findViewById(R.id.btnContinuar)
        var btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        var btnOmitir = findViewById<Button>(R.id.btnOmitir)
        var btnOlvideContra = findViewById<Button>(R.id.btnOlvidarContrasenia)

        btnIniciarSesion.setOnClickListener {
            var correo :String = et_correo.text.toString().trim();
            var contra :String = et_contra.text.toString().trim();
            auth.signInWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithEmail:success")
                        //val user = auth.currentUser
                        //updateUI(user)
                        val intent: Intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, task.exception?.message,
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }

        }

        btnRegistrar.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }
        btnOmitir.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnOlvideContra.setOnClickListener {
            val intent: Intent = Intent(this, RecuperarContrasenia::class.java)
            startActivity(intent)
        }
    }
}