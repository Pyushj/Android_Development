package com.kankinara.firebasetutorial.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kankinara.firebasetutorial.databinding.FragmentAddUserBinding
import com.kankinara.firebasetutorial.model.User

class AddUserFragment:Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private val database: FirebaseDatabase =FirebaseDatabase.getInstance()
    private val myReference:DatabaseReference = database.reference.child("MyUsers")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    fun onClickSubmit(){
        val name:String = binding.name.text.toString()
        val age :Int = 10
        val email :String =binding.email.text.toString()
        val userId:String = myReference.push().key.toString()

        val user = User(userId,name,age,email)
        Log.i(TAG,"User datails : $user")

        myReference.child(userId).push().setValue(user).addOnCompleteListener { task,->
            if(task.isSuccessful){
                Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }else{
                Toast.makeText(requireContext(),"Couldn't add user, Please try again later",Toast.LENGTH_LONG).show()
            }
        }

    }
    companion object{
        const val TAG ="AddUserFragment"
    }
}