package com.example.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.contentsList.ContentListActivity
import com.example.mysolelife.databinding.FragmentBookmarkBinding
import com.example.mysolelife.databinding.FragmentTipBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TipFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TipFragment : Fragment() {
    private lateinit var binding : FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        binding.category1.setOnClickListener {
            val intent = Intent(context,ContentListActivity::class.java)
            intent.putExtra("category","category1")
            startActivity(intent)
        }

        binding.category2.setOnClickListener {
            val intent = Intent(context,ContentListActivity::class.java)
            intent.putExtra("category","category2")
            startActivity(intent)
        }

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment2_to_homeFragment2)
        }

        binding.bookmarktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment2_to_bookmarkFragment2)
        }

        binding.talktap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment2_to_talkFragment2)
        }

        binding.storetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment2_to_storeFragment2)
        }

        return binding.root
    }

}