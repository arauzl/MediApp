package com.amoba.mediapp.models

import com.google.gson.annotations.SerializedName

class Respuesta(
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("pacientes") val pacientes: ArrayList<Paciente>
) {
    override fun toString(): String {
        return "Respuesta(ok=$ok, pacientes=${pacientes})"
    }
}