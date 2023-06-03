package com.amoba.mediapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amoba.mediapp.R
import com.amoba.mediapp.models.ResLogin
import com.amoba.mediapp.models.Usuario
import com.amoba.mediapp.providers.UserProvder
import com.amoba.mediapp.util.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var loginBtn: Button? = null
    var editTextEmail: EditText? = null
    var editTextPass: EditText? = null

    var userPrivider = UserProvder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginBtn = findViewById(R.id.login_btn)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPass = findViewById(R.id.edittext_pass)

        loginBtn?.setOnClickListener { login() }

        getSesion()
    }

    private fun login() {

        val email = editTextEmail?.text.toString()
        val pass = editTextPass?.text.toString()

        if(validateForm(email, pass)) {


            val usuario = Usuario(
                email = email,
                password = pass
            )

            userPrivider.registrar(usuario)?.enqueue(object: Callback<ResLogin>{
                override fun onResponse(call: Call<ResLogin>, response: Response<ResLogin>) {
                    if(response.isSuccessful())
                    {
                        Log.d("MainActivity", "Respuesta ${response.body()}")
                        Toast.makeText(this@MainActivity, "Sesion iniciada",Toast.LENGTH_LONG).show()
                        response.body()?.token?.let { saveToken(it) }
                        response.body()?.usuario?.let { saveSession(it) }
                        goToList()


                    }else
                    {
                        Toast.makeText(this@MainActivity, "Email o contraseña incorrectos",Toast.LENGTH_LONG).show()
                        Log.d("MainActivity", "Error ${response.errorBody().toString()}")
                    }

                    Log.d("MainActivity", "Respuesta ${response}")

                }

                override fun onFailure(call: Call<ResLogin>, t: Throwable) {
                    Log.d("MainActivity", "Se produjo un error ${t.message}")
                    Toast.makeText(this@MainActivity, "La informacion no es valida",Toast.LENGTH_LONG).show()
                }

            })
        }else
        {
            Toast.makeText(this, "La informacion no es valida",Toast.LENGTH_LONG).show()
        }



        //startActivity(i)
    }

    private fun saveSession(data: Usuario) {
        val sharedPref = SharedPref(this)

        sharedPref.save("user", data)
    }

    private fun saveToken(data: String) {
        val sharedPref = SharedPref(this)

        sharedPref.save("token", data)
    }

    private fun goToList() {
        val i = Intent(this, ListActivity::class.java)
        startActivity(i)
    }
    fun String.isEmail(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun getSesion() {
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()) {
            val user = gson.fromJson(sharedPref.getData("user"), Usuario::class.java)
            goToList()
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        if(email.isBlank()) {
            return false
        }

        if(password.isBlank()) {
            return false
        }

        if(!email.isEmail())
        {
            return false
        }

        return true

    }

}