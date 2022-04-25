package segoviano.gonzalez.proyectofinalegresados.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import segoviano.gonzalez.proyectofinalegresados.R
import segoviano.gonzalez.proyectofinalegresados.databinding.FragmentGalleryBinding
import segoviano.gonzalez.proyectofinalegresados.ui.Empleo

class GalleryFragment : Fragment() {
    var empleos: ArrayList<Empleo> = ArrayList()
    private var _binding: FragmentGalleryBinding? = null

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
        val empleo1 = Empleo(1, R.drawable.logo_oracle,"Ingeniero en Software", "3 años o mas", "Ciudad Obregón, Sonora", "Java, SQL, .Net", "Lunes a Viernes, 8:00AM a 5:00PM", "Desarrollo de aplicaciones moviles en empresa de alto renobre", 12000f)
        val empleo2 = Empleo(2, R.drawable.logo_ibm,"Ingeniero en Software", "3 años o mas", "Ciudad Obregón, Sonora", "Java, SQL, .Net", "Lunes a Viernes, 8:00AM a 5:00PM", "Desarrollo de computadoras para escritorio", 12000f)
        val empleo3 = Empleo(3, R.drawable.logo_facebook,"Ingeniero en Software", "3 años o mas", "Ciudad Obregón, Sonora", "Java, SQL, .Net", "Lunes a Viernes, 8:00AM a 5:00PM", "Desarrollo de perfiles falsos para trolear por internet", 12000f)
        val empleo4 = Empleo(4, R.drawable.logo_google_nuevo,"Ingeniero en Software", "3 años o mas", "Ciudad Obregón, Sonora", "Java, SQL, .Net", "Lunes a Viernes, 8:00AM a 5:00PM", "Desarrollo de software para espiar a tus usuarios", 12000f)

        empleos.add(empleo1)
        empleos.add(empleo2)
        empleos.add(empleo3)
        empleos.add(empleo4)
    }
}