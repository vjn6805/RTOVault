package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivitySigninpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signinpage : AppCompatActivity() {
    private val binding: ActivitySigninpageBinding by lazy{
        ActivitySigninpageBinding.inflate(layoutInflater)
    }

    private lateinit var auth:FirebaseAuth

    override fun onStart() {
        super.onStart()
        val curruser : FirebaseUser?=auth.currentUser
        if(curruser!=null) {
            startActivity(Intent(this,Clientreaddata::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signup.setOnClickListener{
            startActivity(Intent(this,signuppage::class.java))
            finish()
        }

        auth=FirebaseAuth.getInstance()
        binding.btnsignin.setOnClickListener{
            val email=binding.edtemail.text.toString()
            val password=binding.edtpass.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this) {task->
                        if(task.isSuccessful) {
                            startActivity(Intent(this,Clientreaddata::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Error Incorrect email and password", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.adminlogin.setOnClickListener {
            startActivity(Intent(this,signinadmin::class.java))
            finish()
        }

    }

}