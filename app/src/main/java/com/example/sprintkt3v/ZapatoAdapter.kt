package com.example.sprintkt3v

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Asegúrate de tener esta importación si la usas para cargar imágenes

class ZapatoAdapter(
    private val zapatos: List<Zapato>, // Usa solo Zapato, ya que está en el mismo paquete
    private val onClick: (Zapato) -> Unit
) : RecyclerView.Adapter<ZapatoAdapter.ZapatoViewHolder>() {

    inner class ZapatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombreZapato)
        val precio: TextView = itemView.findViewById(R.id.precioZapato)
        val descripcion: TextView = itemView.findViewById(R.id.descripcionZapato)
        val imagen: ImageView = itemView.findViewById(R.id.imagenZapato)

        fun bind(zapato: Zapato) {
            nombre.text = zapato.nombre
            precio.text = "$${zapato.precio}"
            descripcion.text = zapato.descripcion

            // Si estás usando Glide para cargar la imagen
            Glide.with(itemView.context)
                .load(zapato.imagenUrl) // Asegúrate de que zapato.imagenUrl esté definido
                .into(imagen)

            itemView.setOnClickListener {
                onClick(zapato)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZapatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_zapato, parent, false)
        return ZapatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZapatoViewHolder, position: Int) {
        holder.bind(zapatos[position])
    }

    override fun getItemCount() = zapatos.size
}
