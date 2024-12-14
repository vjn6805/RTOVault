package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityAdminclientchoiceBinding
import com.example.myvehical.databinding.ActivitySigninadminBinding
import com.google.firebase.auth.FirebaseAuth

class signinadmin : AppCompatActivity() {
    private val binding:ActivitySigninadminBinding by lazy{
        ActivitySigninadminBinding.inflate(layoutInflater)
    }

    private lateinit var  auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        binding.btnadminsignin.setOnClickListener {
            val adminemail=binding.edtadminemail.text.toString()
            val adminpass=binding.edtadminpass.text.toString()

            auth.signInWithEmailAndPassword(adminemail,adminpass)
                .addOnCompleteListener(this) { task->
                    if(task.isSuccessful){
                        startActivity(Intent(this,Adminmainpage::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Unable to Sign In Try Again Later", Toast.LENGTH_SHORT).show()
                    }

                }
        }

        binding.usersignin.setOnClickListener {
            startActivity(Intent(this,Signinpage::class.java))
            finish()
        }


    }
}