package com.example.firebasetestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasetestapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            var email = binding.emailEt.text.toString()
            var password = binding.pwdEt.text.toString()
            auth.createUserWithEmailAndPassword(email, password) // 회원 가입
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        if (auth.currentUser != null) {
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    } else if (result.exception?.message.isNullOrEmpty()) {
                        Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        login(email, password)
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password) // 로그인
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
    }
}