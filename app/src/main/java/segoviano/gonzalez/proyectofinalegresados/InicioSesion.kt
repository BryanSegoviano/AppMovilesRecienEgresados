package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        var btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        var btnOmitir = findViewById<Button>(R.id.btnOmitir)

        btnRegistrar.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }
        btnOmitir.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}