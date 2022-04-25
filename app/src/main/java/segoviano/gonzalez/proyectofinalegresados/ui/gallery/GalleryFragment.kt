package segoviano.gonzalez.proyectofinalegresados.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import segoviano.gonzalez.proyectofinalegresados.databinding.FragmentGalleryBinding

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
//        val peliculaUno = Pelicula(0, getString(R.string.peliUno), getString(R.string.peliUnoSinop), "182", R.drawable.avengers)
//        val peliculaDos = Pelicula(1, getString(R.string.peliDos), getString(R.string.peliDosSinop), "128", R.drawable.civilwar)
//        val peliculaTres = Pelicula(2, getString(R.string.peliTres), getString(R.string.peliTresSinop), "122", R.drawable.joker)
//        val peliculaCuatro = Pelicula(3, getString(R.string.peliCuatro), getString(R.string.peliCuatroSinop), "100", R.drawable.nemo)
//        val peliculaCinco = Pelicula(5, getString(R.string.peliCinco), getString(R.string.peliCincoSinop), "132", R.drawable.shazam)
//        val peliculaSeis = Pelicula(6, getString(R.string.peliSeis), getString(R.string.peliSeisSinop), "151", R.drawable.wonderwoman)
//        val peliculaSiete = Pelicula(7, getString(R.string.peliSiete), getString(R.string.peliSieteSinop), "99", R.drawable.sonic)
//        peliculas.add(peliculaUno)
//        peliculas.add(peliculaDos)
//        peliculas.add(peliculaTres)
//        peliculas.add(peliculaCuatro)
//        peliculas.add(peliculaCinco)
//        peliculas.add(peliculaSeis)
//        peliculas.add(peliculaSiete)
    }
}