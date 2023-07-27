package com.ropersors.rps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ropersors.rps.databinding.FragmentMainmenuBinding

class mainmenu : Fragment() {
    private lateinit var binding: FragmentMainmenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainmenuBinding.inflate(inflater,container,false)
        binding.pveCard.setOnClickListener {
            findNavController().navigate(mainmenuDirections.actionMainmenuToVersusAi())
        }
        binding.pvpCard.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Comming soon", Toast.LENGTH_LONG).show()
        }

        return binding.root

    }

}