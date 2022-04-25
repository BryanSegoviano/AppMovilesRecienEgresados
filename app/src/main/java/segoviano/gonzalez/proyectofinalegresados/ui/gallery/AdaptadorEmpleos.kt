package segoviano.gonzalez.proyectofinalegresados.ui.gallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

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
        var tv_nombre: TextView = vista.findViewById(R.id.tv_nombre)
        var tv_duracion: TextView = vista.findViewById(R.id.tv_duracion)

        var empleo: Empleo = this.empleos[p0]
        iv_img.setImageResource(empleo.imagen)
        tv_nombre.setText(empleo.nombre)

        vista.setOnClickListener{
            val intent: Intent = Intent(this.context, Activity_empleo::class.java)
            intent.putExtra("titulo", empleo.nombre)
            intent.putExtra("sinopsis", empleo.sinopsis)
            intent.putExtra("poster", empleo.imagen)
            this.context.startActivity(intent)
        }

        return vista
    }

}