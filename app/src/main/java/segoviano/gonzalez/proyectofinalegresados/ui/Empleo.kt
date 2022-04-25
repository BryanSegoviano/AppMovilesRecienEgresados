package segoviano.gonzalez.proyectofinalegresados.ui

data class Empleo(
    val id:Int,
    val img:Int,
    val puesto: String,
    val experiencia: String,
    val ubicacion: String,
    val requisitos: String,
    val horario: String,
    val descripcion: String,
    val sueldo: Float
) {
}