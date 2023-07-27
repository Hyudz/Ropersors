package com.ropersors.rps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.ropersors.rps.databinding.FragmentVersusAiBinding
import kotlin.random.Random



class versus_ai : Fragment(R.layout.fragment_versus_ai) {
    private lateinit var binding: FragmentVersusAiBinding

    var player1 = players()
    var player2 = players()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentVersusAiBinding.inflate(inflater, container, false)
        binding.defaultRockCard.setOnClickListener {
            player1.choice = "rock"
            continue_game()
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }
        binding.defaultPaperCard.setOnClickListener {
            player1.choice = "paper"
            continue_game()
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }
        binding.defaultScissorCard.setOnClickListener {
            player1.choice = "scissors"
            continue_game()
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun continue_game() {
        val randomElement: Int = Random.nextInt(0,3)
        when (randomElement) {
            0 -> Toast.makeText(activity?.applicationContext,"Fire",Toast.LENGTH_LONG).show()
            1 -> Toast.makeText(activity?.applicationContext,"Water",Toast.LENGTH_LONG).show()
            2 -> Toast.makeText(activity?.applicationContext,"Leaf",Toast.LENGTH_LONG).show()
        }

        fun p1Life1() {
            var p1Lives: Int = player1.lives - 1
            player1.lives = p1Lives
        }

        fun p1Life2() {
            var p1Lives: Int = player1.lives - 2
            player1.lives = p1Lives
        }

        fun p2Life1() {
            var p2Lives: Int = player2.lives - 1
            player2.lives = p2Lives
        }

        fun p2Life2() {
            var p2Lives: Int = player2.lives - 2
            player2.lives = p2Lives
        }
    }
}