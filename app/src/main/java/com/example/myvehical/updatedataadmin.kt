package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityAdddataadminBinding
import com.example.myvehical.databinding.ActivityUpdatedataadminBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class updatedataadmin : AppCompatActivity() {
    private val binding:ActivityUpdatedataadminBinding by lazy{
        ActivityUpdatedataadminBinding.inflate(layoutInflater)
    }
    private lateinit var dbrefrence:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnupdata.setOnClickListener {
            val number=binding.edtnumber.text.toString()
            val rto=binding.edtrto.text.toString()
            val owner=binding.edtowner.text.toString()
            val name=binding.edtvehicalname.text.toString()

            if(number.isEmpty() || rto.isEmpty() || owner.isEmpty() || name.isEmpty()){
                Toast.makeText(this, "Please Enter Full Information", Toast.LENGTH_SHORT).show()
            }
            else{
                updatedata(number,rto,owner,name)
            }
        }



    }

    private fun updatedata(number:String,rto:String,owner:String,name:String) {
        dbrefrence=FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicaldata= mapOf<String,String>("owner" to owner, "rto" to rto ,"vehicalname" to name)   //name same in data class or as coloumn name in firebase
        dbrefrence.child(number).updateChildren(vehicaldata)
            .addOnSuccessListener {
                binding.edtnumber.text.clear()
                binding.edtrto.text.clear()
                binding.edtowner.text.clear()
                binding.edtvehicalname.text.clear()
                Toast.makeText(this, "Data Updated Succesfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Adminmainpage::class.java))
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Unable to Update Data", Toast.LENGTH_SHORT).show()
            }
    }
}