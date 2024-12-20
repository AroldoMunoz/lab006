package com.example.lab006.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lab006.R

class Ambiente4Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout correspondiente al fragmento
        val view = inflater.inflate(R.layout.fragment_ambiente4, container, false)

        // Configuración del botón de regreso
        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            // Regresar al fragmento anterior en la pila
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
