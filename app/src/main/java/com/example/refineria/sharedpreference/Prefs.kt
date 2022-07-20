package com.example.refineria.sharedpreference

import android.content.Context

class Prefs (val context:Context) {
    val SHARED_NAME = "SharedData"
    val SHARED_USER_NAME = "username"
    val SHARED_USER_PASSWORD = "password"
    val SHARED_USER_ID = "id"

    //perfil
    val SHARED_USER_NOMBRE = "NOMBRE"
    val SHARED_USER_APELLIDOS = "Apellidos"
    val SHARED_USER_EDAD = "Edad"
    val SHARED_USER_SEXO = "Sexo"
    val SHARED_USER_NSS = "Nss"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    //Inicio de sesión
    fun saveName(name:String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }
    fun savePassword(password:String){
        storage.edit().putString(SHARED_USER_PASSWORD, password).apply()
    }
    fun saveId(id:String){
        storage.edit().putString(SHARED_USER_ID, id).apply()
    }
    fun getName():String{
        return storage.getString(SHARED_USER_NAME, "")!!
    }
    fun getPassword():String{
        return storage.getString(SHARED_USER_PASSWORD, "")!!
    }
    fun getId():String{
        return storage.getString(SHARED_USER_ID, "")!!
    }


    //Perfil de usuario
    fun saveNombre(name: String){
        storage.edit().putString(SHARED_USER_NOMBRE, name).apply()
    }
    fun getNombre():String{
        return storage.getString(SHARED_USER_NOMBRE, "")!!
    }
    fun saveApellidos(name: String){
        storage.edit().putString(SHARED_USER_APELLIDOS, name).apply()
    }
    fun getApellidos():String{
        return storage.getString(SHARED_USER_APELLIDOS, "")!!
    }
    fun saveEdad(name: String){
        storage.edit().putString(SHARED_USER_EDAD, name).apply()
    }
    fun getEdad():String{
        return storage.getString(SHARED_USER_EDAD, "")!!
    }
    fun saveSexo(name: String){
        storage.edit().putString(SHARED_USER_SEXO, name).apply()
    }
    fun getSexo():String{
        return storage.getString(SHARED_USER_SEXO, "")!!
    }
    fun saveNss(name: String){
        storage.edit().putString(SHARED_USER_NSS, name).apply()
    }
    fun getNss():String{
        return storage.getString(SHARED_USER_NSS, "")!!
    }



    fun wipe(){
        storage.edit().clear().apply()
    }

}