package com.example.mysolelife.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysolelife.R
import com.example.mysolelife.utils.FBAuth
import com.example.mysolelife.utils.FBRef


class ContentRVAdapter(val context : Context,
                       val items : ArrayList<ContentModel>,
                       val keyList : ArrayList<String>,
                        val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {

//    interface ItemClick {
//        fun onClick(view : View, position: Int)
//    }
//    var itemClick : ItemClick? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)

        Log.d("ContentRVAdapter", keyList.toString())
        Log.d("ContentRVAdapter", bookmarkIdList.toString())
        return Viewholder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

//        if(itemClick != null){
//            holder.itemView.setOnClickListener {
//                itemClick?.onClick(it, position)
//            }
//        }

        holder.bindItem(items[position], keyList[position])
    }
    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bindItem(item: ContentModel, key : String){

            itemView.setOnClickListener {

                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.weburl)
                itemView.context.startActivity(intent)

            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookmarkIdList.contains(key)){
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            }
            else{
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            //북마크
            bookmarkArea.setOnClickListener {
                Log.d("ContentRVAdapter", FBAuth.getUid())
                Toast.makeText(context, key, Toast.LENGTH_LONG).show()

                if(bookmarkIdList.contains(key)){
                    //북마크 있을 때 리스트 삭제
                    //bookmarkIdList.remove(key)
                    
                    //true값 삭제
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue() 

                }else{
                    //북마크가 없을 때
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true)) //data 모델링
                }


                
            }

            contentTitle.text = item.title

            Glide.with(itemView)
                .load(item.imageUrl)
                .into(imageViewArea)

        }

    }

}