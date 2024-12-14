package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivitySignuppageBinding
import com.google.firebase.auth.FirebaseAuth

class signuppage : AppCompatActivity() {
    private val binding:ActivitySignuppageBinding by lazy{
        ActivitySignuppageBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signin.setOnClickListener {
            startActivity(Intent(this,Signinpage::class.java))
            finish()
        }

        auth=FirebaseAuth.getInstance()
        binding.btnsignup.setOnClickListener {
            val name=binding.edtname.text.toString()
            val email=binding.edtemail.text.toString()
            val password=binding.edtpass.text.toString()
            val repassword=binding.edtrepass.text.toString()
            
            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                Toast.makeText(this, "Please Enter all details", Toast.LENGTH_SHORT).show()
            }
            else{
                if(password.equals(repassword)) {
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener (this){task->
                            if(task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                            else{
                                Toast.makeText(this, "Unable to create user Try Again Later", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else{
                    Toast.makeText(this, "Password dose not Match", Toast.LENGTH_SHORT).show()
                }
            }
            

        }

    }
}