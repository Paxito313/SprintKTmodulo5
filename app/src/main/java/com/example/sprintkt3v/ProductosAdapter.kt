package com.example.sprintkt3v

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprintkt3v.ProductosAdapter


class ProductosAdapter(
    private val productos: List<Zapato>,
    private val onClick: (Zapato) -> Unit
) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imagenZapato) // Asegúrate de que este ID coincida
        val nombre: TextView = view.findViewById(R.id.nombreZapato) // Asegúrate de que este ID coincida
        val precio: TextView = view.findViewById(R.id.precioZapato) // Asegúrate de que este ID coincida
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false) // Asegúrate de tener este layout
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val zapato = productos[position]
        holder.nombre.text = zapato.nombre
        holder.precio.text = "$${zapato.precio}"
        Glide.with(holder.itemView.context)
            .load(zapato.imagenUrl)
            .into(holder.imagen)

        holder.itemView.setOnClickListener { onClick(zapato) }
    }

    override fun getItemCount(): Int = productos.size
}
