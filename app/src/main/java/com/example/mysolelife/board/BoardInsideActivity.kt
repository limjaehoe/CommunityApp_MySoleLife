package com.example.mysolelife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityBoardInsideBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()


//        Log.d(TAG, title)
//        Log.d(TAG, content)
//        Log.d(TAG, time)

        val key = intent.getStringExtra("key").toString()
        getImageData(key)
        binding.titleArea.text = title
        binding.textArea.text = content
        binding.timeArea.text = time

    }

    private fun getImageData(key : String){
        // Reference to an image file in Cloud Storage

        Log.e(TAG,key)
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = binding.getImageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){

                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            }else{

            }
        })
    }
}