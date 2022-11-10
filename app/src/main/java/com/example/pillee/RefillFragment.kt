package com.example.pillee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pillee.databinding.FragmentRefillBinding

class RefillFragment : Fragment() {
    private var _binding: FragmentRefillBinding? = null


    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRefillBinding.inflate(inflater, container, false)
        return binding.root
    }
}