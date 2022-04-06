package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistroEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_empresa)

        var btnVolver = findViewById<Button>(R.id.btnVolver)
        var btnContinuar = findViewById<Button>(R.id.btnContinuar)

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}