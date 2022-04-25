package segoviano.gonzalez.proyectofinalegresados.ui.gallery

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

class AdaptadorEmpleos: BaseAdapter {

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
        var vista = inflador.inflate(R.layout.empleo_view, null)

        var iv_img: ImageView = vista.findViewById(R.id.img_view)
        var tv_desc: TextView = vista.findViewById(R.id.tv_desc)

        var empleo: Empleo = this.empleos[p0]
        iv_img.setImageResource(empleo.img)
        tv_desc.setText(empleo.descripcion)

        vista.setOnClickListener{
            val intent: Intent = Intent(this.context, activity_empleo::class.java)
            intent.putExtra("descripcion", empleo.descripcion)
            intent.putExtra("logo", empleo.img)
            this.context.startActivity(intent)
        }

        return vista
    }

}