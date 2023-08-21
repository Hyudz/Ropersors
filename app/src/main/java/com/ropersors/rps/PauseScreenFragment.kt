package com.ropersors.rps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ropersors.rps.databinding.FragmentPauseScreenBinding

class PauseScreenFragment : Fragment() {
    private lateinit var binding: FragmentPauseScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPauseScreenBinding.inflate(inflater, container, false)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(PauseScreenFragmentDirections.actionPauseScreenFragmentToVersusAi())
        }
        binding.restartButton.setOnClickListener {
            findNavController().navigate(PauseScreenFragmentDirections.actionPauseScreenFragmentToVersusAi())
        }
        binding.mainmenuButton.setOnClickListener {
            findNavController().navigate(PauseScreenFragmentDirections.actionPauseScreenFragmentToSplashscreen())
        }
        return binding.root

    }
}
