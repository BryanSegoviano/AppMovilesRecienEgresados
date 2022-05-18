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
import segoviano.gonzalez.proyectofinalegresados.MainActivity
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
        this.empleos = MainActivity.listaEmpleos
    }
}