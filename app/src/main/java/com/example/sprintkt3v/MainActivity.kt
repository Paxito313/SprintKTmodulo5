package com.example.sprintkt3v

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var botonIrACarrito: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el botón que lleva al carrito
        botonIrACarrito = findViewById(R.id.boton_ir_a_carrito)

        // Inicializar y reproducir música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.fondo) // Reemplaza con el nombre de tu archivo de música
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        // Cargar el fragmento inicial (Lista de productos o zapatos)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListaZapatosFragment())
                .commit()
        }

        // Manejar el clic en el botón para ir al carrito
        botonIrACarrito.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CarritoFragment())
                .addToBackStack(null) // Añadir a la pila de retroceso para poder volver
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener y liberar el MediaPlayer al destruir la actividad
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}
