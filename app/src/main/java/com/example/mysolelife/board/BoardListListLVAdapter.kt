package com.example.mysolelife.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mysolelife.R

class BoardListListLVAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(p0: Int): Any {
        return boardList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view = p1
        if (view ==null){
            view = LayoutInflater.from(p2?.context).inflate(R.layout.board_list_item, p2, false)
        }

        val title = view!!.findViewById<TextView>(R.id.titleArea)
        title!!.text = boardList[p0].title

        val cotent = view!!.findViewById<TextView>(R.id.contentArea)
        cotent!!.text = boardList[p0].content

        val time = view!!.findViewById<TextView>(R.id.timeArea)
        time!!.text = boardList[p0].time

        return view!!
    }
}