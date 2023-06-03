package com.amoba.mediapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amoba.mediapp.R
import com.amoba.mediapp.activities.PacienteActivity
import com.amoba.mediapp.models.Paciente
import com.bumptech.glide.Glide
import com.google.gson.Gson

class PacienteAdapter(val context: Activity, val pacientes: ArrayList<Paciente>): RecyclerView.Adapter<PacienteAdapter.PaciViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaciViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return PaciViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pacientes.size
    }

    override fun onBindViewHolder(holder: PaciViewHolder, position: Int) {
        val paciente = pacientes[position]
        holder.textViewPaci.text = paciente.nombre
        holder.textViewEmail.text = paciente.email
        Glide.with(context).load(paciente.avatar).into(holder.imageViewPaci)
        holder.itemView.setOnClickListener { gotoFicha(paciente) }

    }

    private fun gotoFicha(paciente: Paciente) {

        val i = Intent(context, PacienteActivity::class.java)
        i.putExtra("paciente", paciente.toJson())
        context.startActivity(i)

    }

    class PaciViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewPaci: TextView
        val textViewEmail: TextView
        val imageViewPaci: ImageView

        init {
            textViewPaci = view.findViewById(R.id.text_pac)
            textViewEmail = view.findViewById(R.id.text_email_pac)
            imageViewPaci = view.findViewById(R.id.imagen_pac)
        }
    }

}