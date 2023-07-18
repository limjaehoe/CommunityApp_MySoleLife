package com.example.mysolelife.utils

import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    //자바에서 public static final ~ 변수
    companion object{
        private lateinit var auth: FirebaseAuth

        fun getUid() : String{
            auth = FirebaseAuth.getInstance()
            return auth.currentUser?.uid.toString()
        }

        fun getTime() : String{
            val currentDateTime = Calendar.getInstance().time
            val dataFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

            return dataFormat
        }
    }

}