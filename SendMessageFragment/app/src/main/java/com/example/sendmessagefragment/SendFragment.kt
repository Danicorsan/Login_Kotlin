package com.example.sendmessagefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sendmessagefragment.databinding.FragmentSendBinding
import com.example.sendmessagefragment.model.Message
import com.example.sendmessagefragment.model.Persona

class SendFragment : Fragment() {

    private var _binding: FragmentSendBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendBinding.inflate(inflater , container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //opcion 3 Expresion lambda
        binding.floatingActionButton2.setOnClickListener {sendMessage()}
        Log.d(TAG, "SenMessageactivity -> onCreate()")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SendMessageFragment -> onCreate()")
    }

     private fun sendMessage() {
        //2 añadir los obketos al bundle
        val personE = Persona("Jose", "Armando", "49586325R") // Example sender
        val personD = Persona(
            binding.etName.text.toString(),
            binding.etSurname.text.toString(),
            binding.etDni.text.toString()
        )
        val message = Message(personE,personD,binding.etMensaje.text.toString())
        val bundle = Bundle()
         bundle.putParcelable(Message.KEY,message)

        //3 Navegar al fragment ViewFragment
        //findNavController().navigate(R.id.action_sendFragment2_to_viewFragment3,bundle)

         //4. Navegar al fragment con safeargs
         val action = SendFragmentDirections.actionSendFragment2ToViewFragment3(message)
         findNavController().navigate(action)
    }

    companion object {
        const val TAG = "SendFragment"
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SendFragment -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SendFragment -> onDestroy()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SendFragment -> onResume()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SendFragment -> onStart()")
    }
}