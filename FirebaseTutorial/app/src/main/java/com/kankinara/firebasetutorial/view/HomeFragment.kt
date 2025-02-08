package com.kankinara.firebasetutorial.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kankinara.firebasetutorial.databinding.FragmentHomeBinding
import com.kankinara.firebasetutorial.model.User

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserListAdapter
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fragment = this@HomeFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFireBaseDB()
    }

    private fun initRecyclerView() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initFireBaseDB() {
        val userList = mutableListOf<User>()
        myReference.child("MyUsers").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (users in snapshot.children) {
                    val user = users.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                adapter.setUserList(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun onClickAdd() {
        startActivity(Intent(requireContext(), AddUserActivity::class.java))
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_LONG).show()
    }

}