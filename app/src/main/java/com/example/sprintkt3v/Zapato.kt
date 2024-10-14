import android.os.Parcel
import android.os.Parcelable

// Asume que la interfaz Producto se define de la siguiente manera:
interface Producto {
    val nombre: String
    val precio: Double
    val descripcion: String
}

// Clase Zapato implementando Producto y Parcelable
data class Zapato(
    override val nombre: String,
    override val precio: Double,
    override val descripcion: String,
    val imagenUrl: String // Se mantiene el campo imagenUrl
) : Producto, Parcelable { // Implementa Producto aquí

    // Constructor para crear el objeto desde un Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Lee el nombre del parcel
        parcel.readDouble(),       // Lee el precio del parcel
        parcel.readString() ?: "", // Lee la descripción del parcel
        parcel.readString() ?: ""  // Lee la imagenUrl del parcel
    )

    // Escribir los datos del objeto Zapato al Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
        parcel.writeString(descripcion)
        parcel.writeString(imagenUrl)
    }

    // Describe los contenidos del Parcelable (no cambia)
    override fun describeContents(): Int {
        return 0
    }

    // Companion object para CREATOR que crea un objeto Zapato desde un Parcel
    companion object CREATOR : Parcelable.Creator<Zapato> {
        override fun createFromParcel(parcel: Parcel): Zapato {
            return Zapato(parcel)
        }

        override fun newArray(size: Int): Array<Zapato?> {
            return arrayOfNulls(size)
        }
    }
}
