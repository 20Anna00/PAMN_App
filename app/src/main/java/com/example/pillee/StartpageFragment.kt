package com.example.pillee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pillee.databinding.FragmentStartpageBinding
import kotlinx.android.synthetic.main.fragment_startpage.*
import kotlinx.android.synthetic.main.fragment_title.*

class StartpageFragment : Fragment() {
    private var _binding: FragmentStartpageBinding? = null


    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartpageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StartpageButton.setOnClickListener {
            findNavController().navigate(R.id.action_startpageFragment_to_scheduleFragment)
        }

        Startpage2Button.setOnClickListener {
            findNavController().navigate(R.id.action_startpageFragment_to_refillFragment)
        }
    }
}