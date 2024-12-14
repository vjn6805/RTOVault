package com.example.myvehical

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myvehical.databinding.ActivityAdminclientchoiceBinding

class adminclientchoice : AppCompatActivity() {

    private val binding:ActivityAdminclientchoiceBinding by lazy{
        ActivityAdminclientchoiceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnclient.setOnClickListener {
            startActivity(Intent(this,Signinpage::class.java))
            finish()
        }

        //to move to next module activity
        binding.btnadmin.setOnClickListener {
            startActivity(Intent(this,signinadmin::class.java))
            finish()
        }



    }
}