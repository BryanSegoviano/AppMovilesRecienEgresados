package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        var btnEmpresa = findViewById<Button>(R.id.btnEmpresa)
        var btnPersona = findViewById<Button>(R.id.btnUsuario)

        btnEmpresa.setOnClickListener {
            val intent: Intent = Intent(this, RegistroEmpresa::class.java)
            startActivity(intent)
        }

        btnPersona.setOnClickListener{
            val intent: Intent = Intent(this, RegistroPersona::class.java)
            startActivity(intent)
        }

        var btnVolver = findViewById<Button>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }

    }
}