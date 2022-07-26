package com.example.refineria.sharedpreference

import android.content.Context

class Prefs (val context:Context) {
    //variables de usuario
    val SHARED_ID_USUARIO = "id usuario"
    val SHARED_NOMBRE_USUARIO = "nombre usuario"
    val SHARED_ID_CARGO_USUARIO = "cargo"
    val SHARED_TIPO_USUARIO = "tipo"
    val SHARED_USERNAME_USUARIO = "username"
    val SHARED_LUGAR_TOMA_USUARIO="lugar toma"
    val SHARED_PUNTO_TRABAJO_USUARIO = "punto trabajo"
    val SHARED_PROFESION = "profesion"
    val SHARED_CEDULA = "cedula"
    val SHARED_IP = "ip"
    val SHARED_MODELO = "modelo"

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


    //urls para la aplicacion
    val antigenos_obtener_lista = "https://bimo-lab.com/movil/antigenos/api/antigenos_obtener_lista.php"//"https://bimo-lab.com/movil/antigenos/api/antigenos_obtener_lista.php"
    val login_api = "https://bimo-lab.com/movil/antigenos/api/login-api.php"//"https://bimo-lab.com/movil/antigenos/api/login-api.php"
    val filtrar_lista = "https://bimo-lab.com/movil/antigenos/api/filtrar_lista.php"
    val historial = "https://bimo-lab.com/movil/antigenos/api/insert_historial_movil.php"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    //urls
    fun getAntObtenerLista(): String {
        return antigenos_obtener_lista
    }

    fun getLoginApi(): String {
        return login_api
    }

    fun filtrarLista():String{
        return filtrar_lista
    }

    fun getHistorialPhp(): String {
        return historial
    }

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
    fun saveIdUsuario(id: String){
        storage.edit().putString(SHARED_ID_USUARIO, id).apply()
    }

    fun getIdUsuario(): String {
        return storage.getString(SHARED_ID_USUARIO,"")!!
    }

    fun saveNombreUsuario(nombre: String){
        storage.edit().putString(SHARED_NOMBRE_USUARIO,nombre).apply()
    }

    fun getNombreUsuario():String{
        return storage.getString(SHARED_NOMBRE_USUARIO,"")!!
    }

    fun saveIdCargoUsuario(cargo: String){
        storage.edit().putString(SHARED_ID_CARGO_USUARIO,cargo).apply()
    }

    fun getIdCargoUsuario():String{
        return storage.getString(SHARED_ID_CARGO_USUARIO,"")!!
    }

    fun saveTipoUsuario(tipo:String){
        storage.edit().putString(SHARED_TIPO_USUARIO,tipo).apply()
    }

    fun getTipoUsuario():String{
        return storage.getString(SHARED_TIPO_USUARIO,"")!!
    }

    fun saveUsernameUsuario(username:String){
        storage.edit().putString(SHARED_USERNAME_USUARIO,username).apply()
    }

    fun getUsernameUsuario():String{
        return storage.getString(SHARED_USERNAME_USUARIO,"")!!
    }

    fun saveLugarTomaUsuario(lugar:String){
        storage.edit().putString(SHARED_LUGAR_TOMA_USUARIO,lugar).apply()
    }

    fun getLugarTomaUsuario():String{
        return storage.getString(SHARED_LUGAR_TOMA_USUARIO,"")!!
    }

    fun savePuntoTrabajoUsuario(punto:String){
        storage.edit().putString(SHARED_PUNTO_TRABAJO_USUARIO,punto).apply()
    }

    fun getPuntoTrabajoUsuario():String{
        return storage.getString(SHARED_PUNTO_TRABAJO_USUARIO,"")!!
    }

    fun saveProfesionUsuario(punto:String){
        storage.edit().putString(SHARED_PROFESION,punto).apply()
    }

    fun getProfesionUsuario():String{
        return storage.getString(SHARED_PROFESION,"")!!
    }

    fun saveCedulaUsuario(punto:String){
        storage.edit().putString(SHARED_CEDULA,punto).apply()
    }

    fun getCedulaUsuario():String{
        return storage.getString(SHARED_CEDULA,"")!!
    }

    fun saveIPUsuario(punto:String){
        storage.edit().putString(SHARED_IP,punto).apply()
    }

    fun getIPUsuario():String{
        return storage.getString(SHARED_IP,"")!!
    }

    fun saveModeloUsuario(punto:String){
        storage.edit().putString(SHARED_MODELO,punto).apply()
    }

    fun getModeloUsuario():String{
        return storage.getString(SHARED_MODELO,"")!!
    }


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