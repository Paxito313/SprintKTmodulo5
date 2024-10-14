package com.example.sprintkt3v

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Interfaz Producto sin cambios
interface Producto {
    val nombre: String
    val precio: Double
    val descripcion: String
}

// Clase Zapato que implementa Producto
@Parcelize
data class Zapato(
    override val nombre: String,
    override val precio: Double,
    override val descripcion: String,
    val imagenUrl: String, // Campo imagenUrl agregado en Zapato
    var cantidad: Int = 1 // Cantidad por defecto es 1
) : Producto, Parcelable
