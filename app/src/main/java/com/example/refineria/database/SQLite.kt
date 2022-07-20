package com.example.refineria.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            val tablas = arrayOf(
                "CREATE TABLE monitoreo_registro(" +
                        "id_registro text primary key," +
                        "id_trabajador text," +
                        "paramedico int," +
                        "id_frente int," +
                        "ta text," +
                        "tf text," +
                        "temperatura text," +
                        "oximetria text," +
                        "alcoholimetria text," +
                        "doping text," +
                        "otros text," +
                        "apto text," +
                        "fecha_registro text," +
                        "registrado text," +
                        "server int," +
                        "activo int);",
                "CREATE TABLE monitoreo_trabajadores (" +
                        "id_trabajador text primary key," +
                        "nombre text," +
                        "apellidos text," +
                        "edad int," +
                        "fecha_nacimiento text," +
                        "num_trabajador text," +
                        "categoria text," +
                        "servidor int," +
                        "activo int);",
                "CREATE TABLE monitoreo_registro_fotos(" +
                        "id_foto text primary key," +
                        "id_registro text," +
                        "ruta blob," +
                        "activo int);",
                "CREATE TABLE monitoreo_usuarios(" +
                        "id int primary key," +
                        "id_tipo int," +
                        "id_empresa int," +
                        "nombre text," +
                        "apellidos text," +
                        "sexo text," +
                        "edad text," +
                        "fecha_nacimiento text," +
                        "nss text," +
                        "usuario text," +
                        "password text," +
                        "turno text," +
                        "activo int);",
                "CREATE TABLE monitoreo_empresas(" +
                        "id_empresa int primary key," +
                        "descripcion text," +
                        "activo int);",
                "CREATE TABLE monitoreo_tipo_usuario(" +
                        "id_tipo int primary key," +
                        "descripcion text," +
                        "activo int);",
                "CREATE TABLE monitoreo_paramedico_frentes(" +
                        "id_pa_fre int primary key," +
                        "paramedico int," +
                        "id_frente int);",
                "CREATE TABLE monitoreo_frentes(" +
                        "id_frente int primary key," +
                        "nombre_frente text," +
                        "numero_frente text," +
                        "supervisor_obra text," +
                        "supervisor_seguridad text," +
                        "turno text," +
                        "activo int," +
                        "ubicacion text," +
                        "predio text);",
                "CREATE TABLE monitoreo_contrato_frentes(" +
                        "id_con_fre int primary key," +
                        "id_contrato int," +
                        "id_frente int," +
                        "id_empresa int);",
                "CREATE TABLE monitoreo_contratos(" +
                        "id_contrato int primary key," +
                        "contrato text," +
                        "codigo text," +
                        "num_contrato text," +
                        "activo int);"
            )


            for (tabla in tablas) {
                db?.execSQL(tabla)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        }

    }