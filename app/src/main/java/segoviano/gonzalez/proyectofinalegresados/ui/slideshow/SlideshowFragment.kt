package segoviano.gonzalez.proyectofinalegresados.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import segoviano.gonzalez.proyectofinalegresados.R
import segoviano.gonzalez.proyectofinalegresados.databinding.FragmentSlideshowBinding
import segoviano.gonzalez.proyectofinalegresados.ui.Empleo
import segoviano.gonzalez.proyectofinalegresados.ui.gallery.AdaptadorEmpleos

class SlideshowFragment : Fragment() {

    var empleosPostulados: ArrayList<Empleo> = ArrayList()
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //
        llenarEmpleosPostulados()
        var adaptadorEmpleosPostulados = AdaptadorEmpleosPostulados(root.context, this.empleosPostulados)

        var listView: ListView = binding.listviewEmpleosPostulados
        listView.adapter = adaptadorEmpleosPostulados
        //
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun llenarEmpleosPostulados(){
        val empleo1 = Empleo(1, R.drawable.logo_oracle,"Ingeniero en Software", "Experiencia de 3 años o mas", "Austin, Texas", "Java, SQL, .Net", "Lunes a Viernes, 8:00AM a 5:00PM", "Desarrollo de aplicaciones moviles en empresa de alto renombre", 12000f, "Google")
        val empleo4 = Empleo(4, R.drawable.logo_google_nuevo,"Ingeniero en Sistemas ", "1 año de experiencia de preferencia", "Mountain View, California", "Python, R, C++", "Domingo a Viernes, 3:00AM a 11:00PM", "Mantenimiento de los sistemas de la empresa", 15000f, "Facebook")

        empleosPostulados.add(empleo1)
        empleosPostulados.add(empleo4)
    }

}