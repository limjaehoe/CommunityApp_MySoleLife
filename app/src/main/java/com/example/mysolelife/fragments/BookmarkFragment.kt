package com.example.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.databinding.FragmentBookmarkBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

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


}