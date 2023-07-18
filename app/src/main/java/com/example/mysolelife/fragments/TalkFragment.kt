package com.example.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.board.BoardInsideActivity
import com.example.mysolelife.board.BoardListListLVAdapter
import com.example.mysolelife.board.BoardModel
import com.example.mysolelife.board.BoardWriteActivity
import com.example.mysolelife.contentsList.BookmarkRVAdapter
import com.example.mysolelife.contentsList.ContentModel
import com.example.mysolelife.databinding.FragmentTokBinding
import com.example.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TalkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TalkFragment : Fragment() {
    private lateinit var binding : FragmentTokBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private val TAG = TalkFragment::class.java.simpleName
    private var key : String? = null

    private lateinit var boardRVAdapter: BoardListListLVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tok, container, false)

        //val boardList = mutableListOf<BoardModel>()
        //boardList.add(BoardModel("a","b","c","d"))

        boardRVAdapter = BoardListListLVAdapter(boardDataList)
        binding.boardListView.adapter = boardRVAdapter

        binding.boardListView.setOnItemClickListener { adapterView, view, position, id ->

            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("title", boardDataList[position].title)
            intent.putExtra("content", boardDataList[position].content)
            intent.putExtra("time", boardDataList[position].time)
            intent.putExtra("key", boardKeyList[position])

            Log.d(TAG,boardKeyList[position])

            startActivity(intent)
        }

        //첫번째 방법으로 listview에 있는 데이터 title content time 다 다른 액티비티 전달해줘서 만들기
        //두번째 각각의 id값을 전달해줘서, id값으로 데이터를 받아와서 데이터 가져오기


        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment2_to_homeFragment22)
        }

        binding.bookmarktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment2_to_bookmarkFragment2)
        }

        binding.tiptap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment2_to_tipFragment2)
        }

        binding.storetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment2_to_storeFragment2)
        }

        getBoardData()

        return binding.root
    }


    private fun getBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for(dataModel in dataSnapshot.children){

                    Log.d(TAG,dataModel.toString())
                    key = dataSnapshot.key.toString()

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardDataList.reverse()
                boardKeyList.reverse()
                boardRVAdapter.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }

}