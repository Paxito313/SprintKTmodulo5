package com.example.sprintkt3v

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaZapatosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ZapatoAdapter
    private val listaZapatos = listOf(
        Zapato(
            nombre = "Botas de Hierro",
            precio = 30.0,
            descripcion = "Botas muy pesadas, muy útiles cuando se quiere caminar por el fondo marino.",
            imagenUrl = "https://static.wikia.nocookie.net/zelda/images/a/a5/Botas_de_Hierro_TP.png/revision/latest?cb=20110212230654&path-prefix=es"
        ),
        Zapato(
            nombre = "Zapatos para Plomería",
            precio = 10.0,
            descripcion = "Zapatos punta de fierro, muy buenos para pisar hasta la más dura caparazón.",
            imagenUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzabJBQUB_mEGP7x-DB-n15gepMgHsj4kk6A&s"
        ),
        Zapato(
            nombre = "Botas Voladoras",
            precio = 100.0,
            descripcion = "Aunque se llamen voladoras, no suelen volar, solo levitar, perfectas para no caerse con agujeros indeseados.",
            imagenUrl = "https://static.wikia.nocookie.net/zelda/images/7/77/Artwork_botas_voladoras_OoT.png/revision/latest?cb=20150813032308&path-prefix=es"
        ),
        Zapato(
            nombre = "Zapato de Velocidad",
            precio = 59.99,
            descripcion = "Zapatos que aumentan tu velocidad, ideales para carreras.",
            imagenUrl = "https://pbs.twimg.com/media/FPhpeTVWYAQva8q.jpg:large"
        ),
        Zapato(
            nombre = "Botas de un desempleado",
            precio = 69.99,
            descripcion = "Huelen a que esa persona no trabaja.",
            imagenUrl = "https://i.pinimg.com/736x/e4/31/35/e431356f6af4129161d8830cb97f94bf.jpg"
        ),
        Zapato(
            nombre = "Zapatos de oro, edición invisible",
            precio = 99.99,
            descripcion = "Tienen un poderoso hechizo que las hace invisibles e intangibles.",
            imagenUrl = "https://e7.pngegg.com/pngimages/282/843/png-clipart-person-s-feet-with-white-shadow-illustration-barefoot-walking-flat-feet-sole-others-miscellaneous-photography-thumbnail.png"
        ),
        Zapato(
            nombre = "Elza pato",
            precio = 89.99,
            descripcion = "Es solo un meme.",
            imagenUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSVNVoP7Sr2CB1JeIw71Fkae6HmW9Y1qgdnA&s"
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_lista_zapatos, container, false)
        recyclerView = vista.findViewById(R.id.recyclerViewZapatos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ZapatoAdapter(listaZapatos,
            onClick = { zapato ->
                // Navegar al DetalleZapatoFragment
                val detalleFragment = DetalleZapatoFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("zapato", zapato)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detalleFragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        recyclerView.adapter = adapter
        return vista
    }
}
