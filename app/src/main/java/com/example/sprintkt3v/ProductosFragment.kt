package com.example.sprintkt3v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductosAdapter
    private var listaProductos: List<Zapato> = listOf() // Cambia esto a la lista de tus productos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar el adaptador y establecer la lista de productos
        adapter = ProductosAdapter(listaProductos) { zapato ->
            // Manejar el clic en un zapato
            mostrarDetalleZapato(zapato)
        }
        recyclerView.adapter = adapter

        return view
    }

    private fun mostrarDetalleZapato(zapato: Zapato) {
        // Lógica para mostrar los detalles del zapato
        val detalleFragment = DetalleZapatoFragment.newInstance(zapato)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detalleFragment) // Asegúrate de que el ID sea el correcto
            .addToBackStack(null)
            .commit()
    }

}
