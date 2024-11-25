package com.moronlu18.saveactivity

import AnswerAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.moronlu18.saveactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AnswerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AnswerAdapter(this, mutableListOf())
        binding.rvListAnswer.adapter = adapter
        binding.rvListAnswer.layoutManager = LinearLayoutManager(this)

        // Inicializar los datos
        initializeAnswer()

        // Manejo de clics en los ítems del RecyclerView
        adapter.setOnItemClickListener { position, answerText ->
            // Crear el fragment con la posición y la respuesta seleccionada
            val fragment = AnswerFragment.newInstance(position, answerText)

            // Reemplazar el fragmento en el contenedor
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    private fun initializeAnswer() {
        adapter.addAll(
            listOf(
                Answer(true, "Opcion A"),
                Answer(false, "Opcion B"),
                Answer(false, "Opcion C"),
                Answer(false, "Opción D"),
                Answer(false, "Opción E"),
                Answer(false, "Opción F")
            )
        )
    }
}
