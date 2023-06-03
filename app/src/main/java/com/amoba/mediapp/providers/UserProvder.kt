package com.amoba.mediapp.providers

import com.amoba.mediapp.api.ApiRoutes
import com.amoba.mediapp.models.ResLogin
import com.amoba.mediapp.models.Respuesta
import com.amoba.mediapp.models.Usuario
import com.amoba.mediapp.routes.UsersRoutes
import retrofit2.Call

class UserProvder {
    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUserRoutes()

    }

    fun registrar(user: Usuario): Call<ResLogin>? {
        return usersRoutes?.registro(user)
    }

    fun listarPacientes(): Call<Respuesta>? {
        return usersRoutes?.listarPacientes()
    }
}