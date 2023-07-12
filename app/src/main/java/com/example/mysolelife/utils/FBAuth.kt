package com.example.mysolelife.utils

import com.google.firebase.auth.FirebaseAuth

class FBAuth {

    //자바에서 public static final ~ 변수
    companion object{
        private lateinit var auth: FirebaseAuth

        fun getUid() : String{
            auth = FirebaseAuth.getInstance()
            return auth.currentUser?.uid.toString()
        }
    }

}