package com.amoba.mediapp.models

import com.google.gson.annotations.SerializedName

class Usuario(
    @SerializedName("uid") val uid: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("nombre") val nombre: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("rol") val rol: String? = null,

) {

    override fun toString(): String {
        return "Usuario(uid='$uid', email='$email', nombre='$nombre', password='$password', avatar='$avatar', rol='$rol')"
    }
}