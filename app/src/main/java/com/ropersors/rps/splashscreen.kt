package com.ropersors.rps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ropersors.rps.databinding.FragmentSplashscreenBinding

class splashscreen : Fragment() {
    private lateinit var binding: FragmentSplashscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashscreenBinding.inflate(inflater,container,false)

        binding.intro.setOnClickListener {
            findNavController().navigate(splashscreenDirections.actionSplashscreenToMainmenu())
        }
        return binding.root
    }

}