package com.example.refineria.classes

data class PacientesAntigeno (
    val id_paciente: Int,
    val folio: String? = "",
    val folioOrden: String?="",
    val nombre: String,
    val resultado: String?="",
    val fechaResultado: String?="",
    val prefolio: String?="",
    val segmento: String?="",
    val sexo: String,
    val fechaIngreso: String,
    val indicador: String?="",
    val origen: String,
    val lugarExtra: String? = "Ninguno",
    val procedencia: String,
    val edad: Int
        )