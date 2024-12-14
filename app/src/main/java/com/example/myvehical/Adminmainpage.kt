package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityAdminmainpageBinding
import com.google.firebase.auth.FirebaseAuth

class Adminmainpage : AppCompatActivity() {
    private val binding:ActivityAdminmainpageBinding by lazy{
        ActivityAdminmainpageBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.btnupload.setOnClickListener {
            startActivity(Intent(this,adddataadmin::class.java))
            finish()
        }

        binding.btnlogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,adminclientchoice::class.java))
            finish()
        }

        binding.btnupdate.setOnClickListener {
            startActivity(Intent(this,updatedataadmin::class.java))
            finish()
        }

        binding.btndelete.setOnClickListener {
            startActivity(Intent(this,deletedataadmin::class.java))
            finish()
        }

    }
}