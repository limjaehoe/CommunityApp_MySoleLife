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
import com.example.mysolelife.databinding.FragmentTokBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tok, container, false)

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

        return binding.root
    }


}