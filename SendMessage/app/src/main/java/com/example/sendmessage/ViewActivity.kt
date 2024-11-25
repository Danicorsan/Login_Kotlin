package com.example.sendmessage

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sendmessage.databinding.ActivityViewBinding
import com.example.sendmessage.model.Message

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private var message: Message? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        Lo que he hecho yo
            Obtener el objeto Message del Intent
        val message = intent.getParcelableExtra<Message>("message")

           Mostrar los datos en los TextViews
        message?.let {
        binding.tvPersona.text = it.persona.toString()
        binding.tvMensaje.text = it.mensaje.toString()
        }
        */

        //1 Recoger el bundle del intent que se ha recibido
        message = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.extras?.getParcelable(Message.KEY, Message::class.java)
        else
            intent.extras?.getParcelable(Message.KEY)

        initView()
        Log.d(TAG, "SenMessageactivity -> onCreate()")

    }

    private fun initView() {
        message?.let {
            binding.tvPersona.text = message?.personaE.toString()
            binding.tvMensaje.text = message?.mensaje
        } ?: run {
            binding.tvPersona.text = getString(R.string.noUsuario)
            binding.tvMensaje.text = getString(R.string.noMensaje)
        }
    }

    companion object {
        const val TAG = "ViewActivity"
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewActivity -> onDestroy()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewActivity -> onResume()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewActivity -> onStart()")
    }

}
