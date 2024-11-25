package com.moronlu18.saveactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moronlu18.saveactivity.databinding.FragmentAnswerBinding

class AnswerFragment : Fragment() {

    private lateinit var binding: FragmentAnswerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswerBinding.inflate(inflater, container, false)

        // Obtener el argumento pasado al fragmento
        val selectedPosition = arguments?.getInt("selectedPosition", -1) ?: -1
        val selectedAnswer = arguments?.getString("selectedAnswer", "Sin selección")

        // Usar la posición recibida para actualizar la UI
        binding.tvSelectedAnswer.text = "Posición seleccionada: $selectedPosition\nRespuesta: $selectedAnswer"

        return binding.root
    }

    companion object {
        fun newInstance(position: Int, answer: String): AnswerFragment {
            val fragment = AnswerFragment()
            val args = Bundle()
            args.putInt("selectedPosition", position)
            args.putString("selectedAnswer", answer)
            fragment.arguments = args
            return fragment
        }
    }
}
