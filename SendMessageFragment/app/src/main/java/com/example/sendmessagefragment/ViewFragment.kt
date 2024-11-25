package com.example.sendmessagefragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sendmessagefragment.databinding.FragmentViewBinding
import com.example.sendmessagefragment.model.Message

class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    val binding get() = _binding!!
    private var message: Message? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        Log.d(TAG, "SenMessageactivity -> onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1 Recoger el bundle de los argumentos del fragmento
//        message = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
//            arguments?.getParcelable(Message.KEY, Message::class.java)
//        else
//            arguments?.getParcelable(Message.KEY)

        //OPcion 2 recoger el bundle de los argumentos del fragment con safeargs
        message = ViewFragmentArgs.fromBundle(requireArguments()).message
        initView()
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
        const val TAG = "ViewFragment"
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewFragment -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewFragment -> onDestroy()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewFragment -> onResume()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewFragment -> onStart()")
    }

}
