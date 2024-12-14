package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityAdddataadminBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class adddataadmin : AppCompatActivity() {
    private val binding:ActivityAdddataadminBinding by lazy{
        ActivityAdddataadminBinding.inflate(layoutInflater)
    }

    private lateinit var dbrefrence:DatabaseReference
    private lateinit var firebase:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnupload.setOnClickListener {
            val number=binding.edtnumber.text.toString()
            val rto=binding.edtrto.text.toString()
            val owner=binding.edtowner.text.toString()
            val name=binding.edtvehicalname.text.toString()

            if(number.isEmpty() || rto.isEmpty() || owner.isEmpty() || name.isEmpty()){
                Toast.makeText(this, "Please Enter Full Information", Toast.LENGTH_SHORT).show()
            }
            else{
                //db name= Vehicle Information
                dbrefrence=FirebaseDatabase.getInstance().getReference("Vehicle Information")
                val vehicaldata=VehicalData(number,rto,owner,name)
                dbrefrence.child(number).setValue(vehicaldata)
                    .addOnSuccessListener {
                        binding.edtnumber.text.clear()
                        binding.edtrto.text.clear()
                        binding.edtowner.text.clear()
                        binding.edtvehicalname.text.clear()
                        Toast.makeText(this, "Data Saved Succesfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Adminmainpage::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to Add Data Try Again Later", Toast.LENGTH_SHORT).show()
                    }


            }
        }

    }
}