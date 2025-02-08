package com.kankinara.systeminteraction

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kankinara.systeminteraction.databinding.FragmentSmsBinding

class SmsFragment : Fragment() {
    companion object{
        const val TAG  = "SmsFragment"
    }
    private lateinit var binding: FragmentSmsBinding
    private lateinit var smsText:String
    private lateinit var smsNumber:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    fun onClickSend() {
        smsText = binding.smsText.text.toString()
        smsNumber = binding.smsNumber.text.toString()

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.SEND_SMS),
                1
            )
        } else {
            val smsManager:SmsManager
            if(Build.VERSION.SDK_INT >=23){
                smsManager = requireContext().getSystemService<SmsManager>(SmsManager::class.java)
            }else{
                smsManager = SmsManager.getDefault()
            }
            Log.i(TAG,"Context: ${requireContext()} , Activity:${requireActivity()}")
            Log.i(TAG,"Reached here smsManager: $smsManager Number:$smsNumber  Message:$smsText")
            smsManager.sendTextMessage(smsNumber, null, smsText, null, null)

            Toast.makeText(requireContext(),"SMS Sent",Toast.LENGTH_LONG).show()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode ==1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val smsManager:SmsManager = requireContext().getSystemService<SmsManager>(SmsManager::class.java)
            Log.i(TAG,"Context: ${requireContext()} , Activity:${requireActivity()}")
            Log.i(TAG,"Reached here smsManager: $smsManager Number:$smsNumber  Message:$smsText")
            smsManager?.sendTextMessage(smsNumber, null, smsText, null, null)

            Toast.makeText(requireContext(),"SMS Sent",Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(requireContext(),"Permission Not Granted",Toast.LENGTH_LONG).show()
        }
    }
}