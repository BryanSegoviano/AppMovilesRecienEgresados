package segoviano.gonzalez.proyectofinalegresados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import segoviano.gonzalez.proyectofinalegresados.ui.gallery.GalleryFragment

class activity_empleo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleo)

        val database = Firebase.database
        val empleosBD = database.getReference("empleos")

        var btnPostular = findViewById<Button>(R.id.btnPostular)
        var btnVolver = findViewById<Button>(R.id.btnVolver)

        val img: ImageView = findViewById(R.id.img_logo)
        val txtPuesto : TextView = findViewById(R.id.tv_puesto)
        val txtExperiencia : TextView = findViewById(R.id.tv_exp)
        val txtUbicacion : TextView = findViewById(R.id.tv_ubicacion)
        val txtRequisitos : TextView = findViewById(R.id.tv_requisitos)
        val txtHorario : TextView = findViewById(R.id.tv_horario)
        val txtDesc : TextView = findViewById(R.id.tv_desc)
        val txtSueldo : TextView = findViewById(R.id.tv_sueldo)
        val txtNombreEmpresa: TextView = findViewById(R.id.tv_empresa)

        val bundle = intent.extras

        if (bundle != null){
            var logoEmpresa : Int = bundle.getInt("logo")
            var puestoEmpresa : String = bundle.getString("puesto", "")
            var experienciaPuesto : String = bundle.getString("experiencia", "")
            var ubicacionEmpresa : String = bundle.getString("ubicacion", "")
            var requisitosPuesto : String = bundle.getString("requisitos", "")
            var horarioPuesto : String = bundle.getString("horario", "")
            var descripcionPuesto : String = bundle.getString("descripcion", "")
            var sueldoPuesto : Float = bundle.getFloat("sueldo", )
            var nombreEmpresa:String= bundle.getString("empresa","")

            img.setImageResource(logoEmpresa)

            txtNombreEmpresa.setText(nombreEmpresa)
            txtPuesto.setText(puestoEmpresa)
            txtExperiencia.setText(experienciaPuesto)
            txtUbicacion.setText(ubicacionEmpresa)
            txtRequisitos.setText("Requisitos: $requisitosPuesto")
            txtHorario.setText(horarioPuesto)
            txtDesc.setText("Descripción: $descripcionPuesto")
            txtSueldo.setText("Salario: $$sueldoPuesto")
        }

        btnPostular.setOnClickListener {
            auth = Firebase.auth
            val database = Firebase.database
            val usuario = auth.currentUser
            val myRef = database.getReference("usuarios")
            var puesto : String = bundle!!.getString("puesto", "")
            myRef.child(usuario?.uid.toString()).child("postulaciones").child(puesto.toString().trim()).push().setValue(puesto.toString().trim())
            Toast.makeText(this, "¡SE HA POSTULADO CORRECTAMENTE!", Toast.LENGTH_LONG).show()
            finish()
        }

        btnVolver.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}