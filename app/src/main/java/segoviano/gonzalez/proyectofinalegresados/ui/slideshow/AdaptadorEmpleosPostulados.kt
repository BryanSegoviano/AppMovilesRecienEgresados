package segoviano.gonzalez.proyectofinalegresados.ui.slideshow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import segoviano.gonzalez.proyectofinalegresados.R
import segoviano.gonzalez.proyectofinalegresados.activity_empleo
import segoviano.gonzalez.proyectofinalegresados.ui.Empleo

class AdaptadorEmpleosPostulados: BaseAdapter {

    lateinit var context: Context
    var empleos: ArrayList<Empleo> = ArrayList()
    constructor(context: Context, empleos: ArrayList<Empleo>){
        this.context = context
        this.empleos = empleos
    }

    override fun getCount(): Int {
        return this.empleos.size
    }

    override fun getItem(p0: Int): Any {
        return this.empleos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflador = LayoutInflater.from(this.context)
        var vista = inflador.inflate(R.layout.empleopostulado_view, null)

        var iv_img: ImageView = vista.findViewById(R.id.img_view)
        var tv_desc: TextView = vista.findViewById(R.id.tv_desc)

        var empleo: Empleo = this.empleos[p0]
        iv_img.setImageResource(empleo.img)
        tv_desc.setText(empleo.puesto)

        vista.setOnClickListener{
            // en lugar de activity_empleo iria el nuevo activity de empleo postulado
            val intent: Intent = Intent(this.context, activity_empleo::class.java)
            intent.putExtra("puesto", empleo.puesto)
            intent.putExtra("experiencia", empleo.experiencia)
            intent.putExtra("ubicacion", empleo.ubicacion)
            intent.putExtra("requisitos", empleo.requisitos)
            intent.putExtra("horario", empleo.horario)
            intent.putExtra("descripcion", empleo.descripcion)
            intent.putExtra("sueldo", empleo.sueldo)

            intent.putExtra("logo", empleo.img)
            this.context.startActivity(intent)
        }

        return vista
    }

}