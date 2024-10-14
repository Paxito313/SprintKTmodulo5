package com.example.sprintkt3v

import com.example.sprintkt3v.Producto

object Carrito {
    private val productos = mutableListOf<Zapato>() // Asegúrate que sea una lista de Zapato

    fun agregarProducto(producto: Zapato) {
        productos.add(producto)
    }

    fun obtenerProductos(): List<Zapato> {
        return productos // Retorna la lista de productos como una lista de Zapato
    }

    fun vaciarCarrito() {
        productos.clear()
    }
}
