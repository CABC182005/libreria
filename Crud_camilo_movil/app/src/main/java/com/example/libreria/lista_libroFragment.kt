package com.example.libreria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.libreria.Adapter.libroAdapter
import com.example.libreria.Config.config.Companion.urlLibro
import com.example.libreria.models.libro
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class lista_libroFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var libroAdapter: libroAdapter
    private lateinit var libros: MutableList<libro>
    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_libro2, container, false)
        recyclerView=view.findViewById(R.id.recyclerViewLibros)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        libros = mutableListOf()
        libroAdapter = libroAdapter(requireContext(), libros) // Cambia el orden de los parámetros aquí
        recyclerView.adapter = libroAdapter

        requestQueue = Volley.newRequestQueue(activity)
        fetchLibros()

        val btnAtras: Button = view.findViewById(R.id.btnAtras)
        btnAtras.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun fetchLibros() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, urlLibro, null,
            { response ->
                val gson = Gson()
                val librosListType = object : TypeToken<List<libro>>() {}.type
                val librosList: List<libro> = gson.fromJson(response.toString(), librosListType)
                libros.clear()
                libros.addAll(librosList)
                libroAdapter.notifyDataSetChanged()
            },
            { error ->
                Toast.makeText(activity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonArrayRequest)
    }
}
