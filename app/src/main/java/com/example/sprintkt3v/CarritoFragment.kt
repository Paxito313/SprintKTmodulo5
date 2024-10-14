package com.example.sprintkt3v

import android.media.MediaPlayer
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
import java.text.NumberFormat
import java.util.*

class CarritoFragment : Fragment() {

    private lateinit var botonVolver: Button
    private lateinit var botonFinalizarCompra: Button
    private lateinit var botonVaciarCarrito: Button
    private lateinit var textoCarrito: TextView
    private lateinit var imagenProductoCarrito: ImageView
    private lateinit var mediaPlayerCompra: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)

        botonVolver = view.findViewById(R.id.boton_volver)
        botonFinalizarCompra = view.findViewById(R.id.boton_finalizar_compra)
        botonVaciarCarrito = view.findViewById(R.id.boton_vaciar_carrito)
        textoCarrito = view.findViewById(R.id.texto_carrito)
        imagenProductoCarrito = view.findViewById(R.id.imagen_producto_carrito)
        mediaPlayerCompra = MediaPlayer.create(requireContext(), R.raw.comprar)

        mostrarProductosEnCarrito()


        botonVolver.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        botonFinalizarCompra.setOnClickListener {
            finalizarCompra()
        }
        botonVaciarCarrito.setOnClickListener {
            vaciarCarrito()
        }
        return view
    }

    private fun mostrarProductosEnCarrito() {
        val productos = Carrito.obtenerProductos()
        if (productos.isEmpty()) {
            textoCarrito.text = "El carrito está vacio."
        } else {
            val sb = StringBuilder()
            for (producto in productos) {
                if (producto is Zapato) {
                    val precioCLP = convertirADivisaLocal(producto.precio)

                    sb.append("${producto.nombre} - $${precioCLP}\n")

                    Glide.with(this)
                        .load(producto.imagenUrl)
                        .into(imagenProductoCarrito)
                }
            }
            textoCarrito.text = sb.toString()
        }
    }

    private fun convertirADivisaLocal(precioUSD: Double): String {
        val tasaConversion = 800.0
        val precioCLP = precioUSD * tasaConversion
        val formatoCLP = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formatoCLP.format(precioCLP)
    }

    private fun finalizarCompra() {
        if (Carrito.obtenerProductos().isEmpty()) {
            Toast.makeText(requireContext(), "El carrito esta vacio. Agrega productos antes de finalizar la compra.", Toast.LENGTH_SHORT).show()
        } else {
            mediaPlayerCompra.start()
            Toast.makeText(requireContext(), "Compra finalizada exitosamente.", Toast.LENGTH_SHORT).show()
            Carrito.vaciarCarrito()
            mostrarProductosEnCarrito()
        }
    }

    private fun vaciarCarrito() {
        Carrito.vaciarCarrito()
        mostrarProductosEnCarrito()
        Toast.makeText(requireContext(), "El carrito ha sido vaciado.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerCompra.release()
    }
}
