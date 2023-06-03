package com.amoba.mediapp.api

import com.amoba.mediapp.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://3.16.112.203:3000/api/"
    val retrofit = RetrofitCliente()

    fun getUserRoutes(): UsersRoutes {
        return retrofit.getCliente(API_URL).create(UsersRoutes::class.java)
    }

}