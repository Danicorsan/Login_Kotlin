package com.example.sendmessage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sendmessage.databinding.ActivitySendBinding
import com.example.sendmessage.model.Message
import com.example.sendmessage.model.Persona

class SendActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Lo que he hecho yo
        /*
        binding.floatingActionButton2.setOnClickListener {
            val nombre = binding.etName.text.toString()
            val apellido = binding.etSurname.text.toString()
            val dni = binding.etDni.text.toString()
            val mensaje = binding.etMensaje.text.toString()

            val persona = Persona(nombre, apellido, dni)
            val message = Message(persona, persona, mensaje)

            val intent = Intent(this, ViewActivity::class.java)
            intent.putExtra("message", message)
            startActivity(intent)
        }


        Opcion dos

        val listener = object : View.OnClickListener{
            override fun onClick(view: View?) {
                when (view?.id){
                    R.id.floatingActionButton2 -> sendMessage()
                    //R.id.btText -> viewMessage()
                }

            }


        }
        binding.floatingActionButton2.setOnClickListener(listener)

        */

        //opcion 3 Expresion lambda
        binding.floatingActionButton2.setOnClickListener {sendMessage()}
        Log.d(TAG, "SenMessageactivity -> onCreate()")

    }

     private fun sendMessage() {
        //1 Se crea el objeto bundle
        val bundle = Bundle()
        //2 añadir los obketos al bundle
        val personE = Persona("Jose", "Armando", "49586325R") // Example sender
        val personD = Persona(
            binding.etName.text.toString(),
            binding.etSurname.text.toString(),
            binding.etDni.text.toString()
        )
        val message = Message(personE,personD,binding.etMensaje.text.toString())

        //3 crear
        val intent = Intent(this, ViewActivity::class.java)

         //4 Añadir el mensaje al bundle y pasarlo al intent
         bundle.putParcelable(Message.KEY,message)
         intent.putExtras(bundle)

        //5 inicie la actividad destino
        startActivity(intent)

    }

    companion object{
        const val TAG="SendActivity"
    }
}