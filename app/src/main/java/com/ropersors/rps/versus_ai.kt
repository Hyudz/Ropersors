package com.ropersors.rps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ropersors.rps.databinding.FragmentVersusAiBinding

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
            continue_game("rock")
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }
        binding.defaultPaperCard.setOnClickListener {
            continue_game("paper")
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }
        binding.defaultScissorCard.setOnClickListener {
            continue_game("scissors")
//            Toast.makeText(activity?.applicationContext, player1.choice, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun continue_game(player_choice: String) {
        player1.choice = player_choice
        val choices = arrayListOf<String>("rock","paper","scissors").random()
        val coumputerCard = binding.defaultEnemyCard

        if (choices == "rock") {
            binding.defaultEnemyCard.animate().apply {
                duration = 500
                rotationY(90f)
            }.withEndAction {
                coumputerCard.setImageResource(R.drawable.default_rock_card)
                binding.defaultEnemyCard.animate().apply {
                    duration = 500
                    rotationY(0f)
                }
            }.start()
        } else if (choices == "paper") {
            binding.defaultEnemyCard.animate().apply {
                duration = 500
                rotationY(90f)
            }.withEndAction {
                coumputerCard.setImageResource(R.drawable.default_paper_card)
                binding.defaultEnemyCard.animate().apply {
                    duration = 500
                    rotationY(0f)
                }
            }.start()
        } else if (choices == "scissors") {
            binding.defaultEnemyCard.animate().apply {
                duration = 500
                rotationY(90f)
            }.withEndAction {
                coumputerCard.setImageResource(R.drawable.default_scissors_card)
                binding.defaultEnemyCard.animate().apply {
                    duration = 500
                    rotationY(0f)
                }
            }.start()
        }

        checkWinner(choices)

    }

    fun checkWinner(computerChoice: String) {
        player2.choice = computerChoice
        if ((player1.choice == "rock" && player2.choice == "paper") ||
            (player1.choice == "paper" && player2.choice =="scissors") ||
            (player1.choice == "scissors" && player2.choice == "rock")) {
            Toast.makeText(activity?.applicationContext,"You lost",Toast.LENGTH_SHORT).show()
        } else if ((player2.choice == "rock" && player1.choice == "paper") ||
            (player2.choice == "paper" && player1.choice =="scissors") ||
            (player2.choice == "scissors" && player1.choice == "rock")){
            Toast.makeText(activity?.applicationContext,"You won",Toast.LENGTH_SHORT).show()
        } else if ((player1.choice == "rock" && player2.choice == "rock") ||
            (player1.choice == "paper" && player2.choice == "paper") ||
            (player1.choice == "scissors" && player2.choice == "scissors")) {
            Toast.makeText(activity?.applicationContext,"Draw",Toast.LENGTH_SHORT).show()
        }
    }
}