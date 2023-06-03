package com.amoba.mediapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.amoba.mediapp.R
import com.amoba.mediapp.models.Paciente
import com.bumptech.glide.Glide
import com.google.gson.Gson

class PacienteActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    val gson = Gson()

    var texto: TextView? = null
    var textViewTitulo: TextView? = null
    var textViewCi: TextView? = null
    var textViewEmail: TextView? = null
    var textViewEdad: TextView? = null
    var textViewSexo: TextView? = null
    var textViewDir: TextView? = null
    var textViewFono: TextView? = null
    var textViewMap: TextView? = null
    var imageViewAvatar: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)
        paciente = gson.fromJson(intent.getStringExtra("paciente"), Paciente::class.java)


        textViewTitulo = findViewById(R.id.text_ficha_pac)
        textViewCi = findViewById(R.id.text_ficha_ci)
        textViewEmail = findViewById(R.id.text_ficha_email)
        textViewEdad = findViewById(R.id.text_ficha_edad)
        textViewSexo = findViewById(R.id.text_ficha_sexo)
        textViewDir = findViewById(R.id.text_ficha_dir)
        textViewFono = findViewById(R.id.text_ficha_fono)
        textViewMap = findViewById(R.id.text_ficha_map)
        imageViewAvatar = findViewById(R.id.imagen_ficha_pac)

        textViewTitulo?.text = paciente?.nombre
        textViewCi?.text = paciente?.cedula
        textViewEmail?.text = paciente?.email
        textViewEdad?.text = paciente?.edad
        textViewSexo?.text = paciente?.sexo
        textViewDir?.text = paciente?.dir
        textViewFono?.text = paciente?.telefono

        textViewMap?.setOnClickListener { goToMap() }

        Glide.with(this).load(paciente?.avatar).into(imageViewAvatar!!)



    }

    private fun goToMap() {
        val i = Intent(this, MapActivity::class.java)
        Log.d("COOR", "LAT ${paciente?.latitude}")
        Log.d("COOR", "LNG ${paciente?.longitude}")
        i.putExtra("lat", paciente?.latitude );
        i.putExtra("lng", paciente?.longitude);
        startActivity(i)
    }

}