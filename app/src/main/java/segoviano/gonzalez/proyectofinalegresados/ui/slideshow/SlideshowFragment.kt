package segoviano.gonzalez.proyectofinalegresados.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import segoviano.gonzalez.proyectofinalegresados.MainActivity
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
        this.empleosPostulados = MainActivity.listaEmpleosPostulados
    }

}