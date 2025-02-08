package com.kankinara.systeminteraction

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kankinara.systeminteraction.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {
    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailBinding.inflate(inflater, container, false)
        binding.fragment = this@EmailFragment
        return binding.root
    }

    fun onclickSend() {
        Toast.makeText(requireContext(), "You clicked send", Toast.LENGTH_SHORT).show()
        val address = arrayOf(binding.editEmailAddress.text.toString())
        val subject = binding.subject.text.toString()
        val message = binding.message.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
//        intent.data = Uri.parse("mailto")
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(intent, "Select an app"))
        } catch (e: Exception) {
            Log.i(TAG, "Error ${e.message}")
            Log.e(TAG, "StackTrace ${e.printStackTrace()}")
            Toast.makeText(requireContext(), "No Email app installed", Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        const val TAG = "EmailFragment"
    }
}