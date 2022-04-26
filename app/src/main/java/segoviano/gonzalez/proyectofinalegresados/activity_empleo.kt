package segoviano.gonzalez.proyectofinalegresados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class activity_empleo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleo)

        val img: ImageView = findViewById(R.id.img_logo)
        val txtPuesto : TextView = findViewById(R.id.tv_puesto)
        val txtExperiencia : TextView = findViewById(R.id.tv_exp)
        val txtUbicacion : TextView = findViewById(R.id.tv_ubicacion)
        val txtRequisitos : TextView = findViewById(R.id.tv_requisitos)
        val txtHorario : TextView = findViewById(R.id.tv_horario)
        val txtDesc : TextView = findViewById(R.id.tv_desc)
        val txtSueldo : TextView = findViewById(R.id.tv_sueldo)

        val bundle = intent.extras

        if (bundle != null){
            var logoEmpresa : Int = bundle.getInt("logo")
            var puestoEmpresa : String = bundle.getString("puesto", "")
            var experienciaPuesto : String = bundle.getString("experiencia", "")
            var ubicacionEmpresa : String = bundle.getString("ubicacion", "")
            var requisitosPuesto : String = bundle.getString("requisitos", "")
            var horarioPuesto : String = bundle.getString("horario", "")
            var descripcionPuesto : String = bundle.getString("descripcion", "")
            var sueldoPuesto : String = bundle.getString("sueldo", "")

            img.setImageResource(logoEmpresa)
            txtPuesto.setText(puestoEmpresa)
            txtExperiencia.setText(experienciaPuesto)
            txtUbicacion.setText(ubicacionEmpresa)
            txtRequisitos.setText(requisitosPuesto)
            txtHorario.setText(horarioPuesto)
            txtDesc.setText(descripcionPuesto)
            txtSueldo.setText(sueldoPuesto)

        }

    }

}