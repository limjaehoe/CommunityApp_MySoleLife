package com.example.mysolelife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R
import com.example.mysolelife.contentsList.BookmarkRVAdapter
import com.example.mysolelife.contentsList.ContentModel
import com.example.mysolelife.databinding.FragmentBookmarkBinding
import com.example.mysolelife.utils.FBAuth
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
 * Use the [BookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkBinding

    private val TAG = BookmarkFragment::class.java.simpleName

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter: BookmarkRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

        //2. 사용자가 북마크한 정보를 다 가져옴
        getBookmarkData()


        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)
        val rv : RecyclerView = binding.bookmarkRV
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(),2)



        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment2_to_homeFragment2)
        }

        binding.storetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment2_to_storeFragment2)
        }

        binding.tiptap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment2_to_tipFragment2)
        }

        binding.talktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment2_to_talkFragment2)
        }

        return binding.root
    }

    private fun getCategoryData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                    Log.e(TAG, dataModel.toString())
                    val item = dataModel.getValue(ContentModel::class.java)

                    //3. 전체 컨텐츠 중에서, 사용자가 북마크한 정보만을 보여줌!
                    if(bookmarkIdList.contains(dataModel.key.toString())){
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }


                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)

    }
    private fun getBookmarkData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for(dataModel in dataSnapshot.children){
                    Log.d(TAG,dataModel.toString())
                    bookmarkIdList.add(dataModel.key.toString())
                }

                //1. 전체 카테고리에 있는 컨텐츠 데이터들을 다 가져옴
                getCategoryData()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }

}