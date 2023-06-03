package com.amoba.mediapp.routes

import com.amoba.mediapp.models.ResLogin
import com.amoba.mediapp.models.Respuesta
import com.amoba.mediapp.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersRoutes {

    @POST("login")
    fun registro(@Body user: Usuario): Call<ResLogin>

    @GET("pacientes")
    fun listarPacientes(): Call<Respuesta>


}