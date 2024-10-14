package com.example.sprintkt3v

import android.media.MediaPlayer // Importa MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetalleZapatoFragment : Fragment() {

    private lateinit var zapato: Zapato
    private lateinit var mediaPlayer: MediaPlayer // Declara la variable para MediaPlayer

    companion object {
        fun newInstance(zapato: Zapato): DetalleZapatoFragment {
            val fragment = DetalleZapatoFragment()
            val args = Bundle().apply {
                putParcelable("zapato", zapato) // Almacena el objeto Zapato en el Bundle
            }
            fragment.arguments = args // Establece el Bundle como argumentos del fragmento
            return fragment // Devuelve la nueva instancia del fragmento
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalle_zapato, container, false)

        // Recuperar el zapato desde los argumentos
        zapato = arguments?.getParcelable("zapato") ?: throw IllegalArgumentException("Zapato no encontrado")

        // Configura la vista con los datos del zapato
        val nombreZapato: TextView = view.findViewById(R.id.nombreZapatoDetalle)
        val precioZapato: TextView = view.findViewById(R.id.precioZapatoDetalle)
        val descripcionZapato: TextView = view.findViewById(R.id.descripcionZapato)
        val imagenZapato: ImageView = view.findViewById(R.id.imagenZapatoDetalle)
        val btnAgregarCarrito: Button = view.findViewById(R.id.botonAgregarCarrito)
        val btnVolver: Button = view.findViewById(R.id.botonVolver) // Botón Volver

        // Establecer los datos del zapato en las vistas
        nombreZapato.text = zapato.nombre
        precioZapato.text = "$${zapato.precio}" // Añade el símbolo de moneda
        descripcionZapato.text = zapato.descripcion

        // Cargar la imagen del zapato usando Glide
        Glide.with(this).load(zapato.imagenUrl).into(imagenZapato)

        // Inicializar el MediaPlayer con el sonido
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.cluclu) // Reemplaza 'sonido_agregar_carrito' con el nombre real del archivo de sonido

        // Botón para agregar el zapato al carrito
        btnAgregarCarrito.setOnClickListener {
            Carrito.agregarProducto(zapato) // Agrega el zapato al carrito
            mediaPlayer.start() // Reproduce el sonido
            Toast.makeText(requireContext(), "${zapato.nombre} agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        // Botón para volver al fragmento anterior
        btnVolver.setOnClickListener {
            parentFragmentManager.popBackStack() // Regresar al fragmento anterior
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Libera el MediaPlayer al destruir el fragmento
    }
}
