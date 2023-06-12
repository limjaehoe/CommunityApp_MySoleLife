package com.example.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.tiptap.setOnClickListener {
            Toast.makeText(context,"클릭",Toast.LENGTH_SHORT).show()

            it.findNavController().navigate(R.id.action_homeFragment2_to_tipFragment2)
        }

        binding.bookmarktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_bookmarkFragment2)
        }
        binding.talktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_talkFragment2)
        }
        binding.storetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_storeFragment2)
        }





        // Inflate the layout for this fragment
        return binding.root


    }

}