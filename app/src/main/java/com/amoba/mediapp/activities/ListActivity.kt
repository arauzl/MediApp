package com.amoba.mediapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.amoba.mediapp.R
import com.amoba.mediapp.adapters.PacienteAdapter
import com.amoba.mediapp.models.ResLogin
import com.amoba.mediapp.models.Respuesta
import com.amoba.mediapp.models.Usuario
import com.amoba.mediapp.providers.UserProvder
import com.amoba.mediapp.util.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    var recyclerPacientes: RecyclerView? = null
    var adapter: PacienteAdapter? = null
    var respuesta: Respuesta? = null
    var userPrivider = UserProvder()
    var btnOut: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerPacientes = findViewById(R.id.recycle_pacientes)
        recyclerPacientes?.layoutManager = LinearLayoutManager(this)

        btnOut = findViewById(R.id.cerrar_btn)
        btnOut?.setOnClickListener { logout() }

        getSesion()


    }

    private fun logout() {
        val sharedPref = SharedPref(this)

        sharedPref?.remove("user")
        sharedPref?.remove("token")

        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }

    private fun getSesion() {
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()) {
            val user = gson.fromJson(sharedPref.getData("user"), Usuario::class.java)
            Log.d("ListActivity", "Usuario: ${user}")
        }

        userPrivider.listarPacientes()?.enqueue(object: Callback<Respuesta>{
            override fun onResponse(call: Call<Respuesta>, response: Response<Respuesta>) {
                if(response.isSuccessful())
                {
                    Log.d("ListActivity", "Respuesta ${response.body()}")
                    Toast.makeText(this@ListActivity, "Sesion iniciada", Toast.LENGTH_LONG).show()
                    respuesta = response.body()

                    adapter = PacienteAdapter(this@ListActivity, respuesta?.pacientes!!)
                    recyclerPacientes?.adapter = adapter

                    recyclerPacientes?.addItemDecoration(
                        DividerItemDecoration(this@ListActivity, LinearLayoutManager.VERTICAL)
                    )

                }else
                {
                    Toast.makeText(this@ListActivity, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show()
                    Log.d("ListActivity", "Error ${response.errorBody().toString()}")
                }

                Log.d("ListActivity", "Respuesta ${response}")
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                Log.d("ListActivity", "Se produjo un error ${t.message}")
                Toast.makeText(this@ListActivity, "La informacion no es valida",Toast.LENGTH_LONG).show()
            }
        })
    }
}