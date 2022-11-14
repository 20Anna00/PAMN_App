package com.example.pillee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pillee.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : Fragment(){

    private var _binding: FragmentTitleBinding? = null


    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TestButton.setOnClickListener{
            findNavController().navigate(R.id.action_titleFragment_to_loginFragment)
        }

        Test2Button.setOnClickListener{
            findNavController().navigate(R.id.action_titleFragment_to_signupFragment)
        }
    }


}