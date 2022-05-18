package segoviano.gonzalez.proyectofinalegresados.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import segoviano.gonzalez.proyectofinalegresados.R
import segoviano.gonzalez.proyectofinalegresados.databinding.FragmentGalleryBinding
import segoviano.gonzalez.proyectofinalegresados.ui.Empleo

class GalleryFragment : Fragment() {
    var empleos: ArrayList<Empleo> = ArrayList()
    private var _binding: FragmentGalleryBinding? = null

    private val auth = Firebase.auth
    val database = Firebase.database
    val empleosDB = database.getReference("empleos")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        llenarEmpleos()
        var adaptadorEmpleo = AdaptadorEmpleos(root.context, this.empleos)

        var listView: ListView = binding.listviewEmpleos
        listView.adapter = adaptadorEmpleo

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun llenarEmpleos(){
        val empleo2 = Empleo(2, R.drawable.logo_ibm,"Arquitecto de Sofware", "Sin experiencia", "Armonk, Nueva York", "C++, C#, C", "Lunes a Sábado, 6:00AM a 3:00PM", "Desarrollo de estrategias y planificación en proyectos de software", 8000f, "IBM")
        val empleo3 = Empleo(3, R.drawable.logo_facebook,"Cientifico de datos", "Experiencia de 1 año o mas", "Menlo Park, California", "Kotlin, Java", "6 dias a la semana, 8:00AM a 5:00PM", "Big Data y análisis de datos de los usuarios", 14500f, "Facebook")
        val empleo4 = Empleo(4, R.drawable.logo_oracle,"Ingeniero en Sistemas ", "1 año de experiencia de preferencia", "Mountain View, California", "Python, R, C++", "Domingo a Viernes, 3:00AM a 11:00PM", "Mantenimiento de los sistemas de la empresa", 15000f, "Oracle")

        empleos.add(empleo2)
        empleos.add(empleo3)
        empleos.add(empleo4)
//            empleosDB.child("google").get().addOnSuccessListener {
//                if(it.exists()){
//                    var puesto = it.child("puesto").value.toString()
//                    var experiencia = it.child("experiencia").value.toString()
//                    var ubicacion = it.child("ubicacion").value.toString()
//                    var requisitos = it.child("requisitos").value.toString()
//                    var horario = it.child("horario").value.toString()
//                    var descripcion = it.child("descripcion").value.toString()
//                    var sueldo = it.child("sueldo").value.toString()
//                    var empresa = empleosDB.child("google").key.toString()
//
//                    val empleo1 = Empleo(1,
//                        R.drawable.logo_google_nuevo,
//                        puesto,
//                        experiencia,
//                        ubicacion,
//                        requisitos,
//                        horario,
//                        descripcion,
//                        sueldo.toFloat(),
//                        empresa)
//                    Log.d("TAG", empleo1.toString())
//                    empleos.add(empleo1)
//                }
//            }


    }
}