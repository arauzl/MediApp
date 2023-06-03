package com.amoba.mediapp.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Paciente(
    @SerializedName("_id") val uid: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("nombre") val nombre: String? = null,
    @SerializedName("cedula") val cedula: String? = null,
    @SerializedName("edad") val edad: String? = null,
    @SerializedName("sexo") val sexo: String? = null,
    @SerializedName("dir") val dir: String? = null,
    @SerializedName("telefono") val telefono: String? = null,
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null
) {

    fun toJson(): String {
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Paciente(uid=$uid, email=$email, nombre=$nombre, cedula=$cedula, edad=$edad, sexo=$sexo, dir=$dir, telefono=$telefono, avatar=$avatar, latitude=$latitude, longitude=$longitude)"
    }
}