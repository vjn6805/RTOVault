package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityClientreaddataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Clientreaddata : AppCompatActivity() {

    private val binding:ActivityClientreaddataBinding by lazy{
        ActivityClientreaddataBinding.inflate(layoutInflater)
    }
    private lateinit var dbrefrence:DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btngetdata.setOnClickListener {
            val number:String=binding.edtnumber.text.toString()
            if(number.isEmpty()) {
                Toast.makeText(this, "No Data Entered Please Enter Data", Toast.LENGTH_SHORT).show()
            }
            else{
                readdata(number)
            }
        }
        auth=FirebaseAuth.getInstance()
        binding.btnlogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,adminclientchoice::class.java))
            finish()
        }


    }

    private fun readdata(vehicalnumber:String) {
        dbrefrence=FirebaseDatabase.getInstance().getReference("Vehicle Information")
        dbrefrence.child(vehicalnumber).get()
            .addOnSuccessListener {
                if(it.exists()) {
                    val name=it.child("owner").value
                    val brand=it.child("vehicalname").value
                    val rto=it.child("rto").value
                    Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                    binding.txtname.text=name.toString()
                    binding.txtbrand.text=brand.toString()
                    binding.txtrto.text=rto.toString()
                    binding.edtnumber.text.clear()
                }
                else{
                    Toast.makeText(this, "Vehicle Number does not exists", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Something went wrong Try Again Later", Toast.LENGTH_SHORT).show()
            }
    }
}