package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityDeletedataadminBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class deletedataadmin : AppCompatActivity() {
    private val binding:ActivityDeletedataadminBinding by lazy{
        ActivityDeletedataadminBinding.inflate(layoutInflater)
    }
    private lateinit var dbrefrence:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnupload.setOnClickListener {
            val number=binding.edtnumber.text.toString()
            if(number.isEmpty()) {
                Toast.makeText(this, "Enter Vehical Number", Toast.LENGTH_SHORT).show()
            }
            else{
                deletedata(number)
            }
        }

    }

    private fun deletedata(number:String) {
        dbrefrence=FirebaseDatabase.getInstance().getReference("Vehicle Information")
        dbrefrence.child(number).removeValue()
            .addOnSuccessListener {
                binding.edtnumber.text.clear()
                Toast.makeText(this, "Data Deleted Succesfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Adminmainpage::class.java))
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Unable to Delete Data", Toast.LENGTH_SHORT).show()
            }
    }
}