package com.amoba.mediapp.models

import com.google.gson.annotations.SerializedName

class ResLogin(
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("token") val token: String,
    @SerializedName("usuario") val usuario: Usuario
) {
    override fun toString(): String {
        return "ResLogin(ok=$ok, token='$token', usuario=$usuario)"
    }
}