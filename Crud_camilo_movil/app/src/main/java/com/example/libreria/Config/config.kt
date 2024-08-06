package com.example.libreria.Config
//se crea una url static, para consultar sin instanciar
//método companion object sirve para almacenar las variables static
class config {

    companion object{
        val urlBase="http://192.168.0.8:8080/api/v1/"
        val urlLibro = urlBase+"libro/"


    }
}