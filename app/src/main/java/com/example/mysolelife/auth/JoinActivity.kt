package com.example.mysolelife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mysolelife.MainActivity
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_join )
        auth = Firebase.auth // Initialize Firebase Auth

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "이메일 입력해주세요",Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if(password1.isEmpty()){
                Toast.makeText(this, "password1 입력해주세요",Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if(password2.isEmpty()){
                Toast.makeText(this, "password2 입력해주세요",Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            //비밀번호가 두개가 같은지 확인
            if(!password1.equals(password2)){
                Toast.makeText(this, "password를 같게 해주세요ㅁ",Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if(password1.length<6){
                Toast.makeText(this, "비밀번호 6자리 이상으로 입력해주세요",Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if(isGoToJoin)
            {
                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"성공",Toast.LENGTH_SHORT).show()

                        val intent = Intent(this,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,"실패",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }






    }
}