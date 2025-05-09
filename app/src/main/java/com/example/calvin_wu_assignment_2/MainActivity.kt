package com.example.calvin_wu_assignment_2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.calvin_wu_assignment_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Beetle Game")
        builder.setIcon(R.drawable.beetle)
        builder.setMessage("Hit Roll to start the game")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()

        gameViewModel.player1Score.observe(this) { score ->
            binding.player1Score.text = "Player 1 Score: $score"
        }

        gameViewModel.player2Score.observe(this) { score ->
            binding.player2Score.text = "Player 2 Score: $score"
        }

        binding.rollButton.setOnClickListener {
            val result = gameViewModel.rollDice()

            Toast.makeText(this, result.resultMessage, Toast.LENGTH_SHORT).show()

            val diceResId = when (result.roll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            if (result.roller == "Player 1") {
                binding.playerDice.setImageResource(diceResId)
                binding.turn.text = "Player 2's turn"
            } else {
                binding.computerPlayer.setImageResource(diceResId)
                binding.turn.text = "Player 1's turn"
            }

            result.winner?.let { winner ->
                Toast.makeText(this, "$winner wins!", Toast.LENGTH_LONG).show()
                binding.rollButton.isEnabled = false
                binding.turn.text = "$winner has won!"

                if (winner == "Player 1") {
                    binding.player1BeetleImage.visibility = View.VISIBLE
                } else {
                    binding.player2BeetleImage.visibility = View.VISIBLE
                }
                showWinAlert(winner)
            }
        }

        binding.resetButton.setOnClickListener {
            gameViewModel.resetGame()
            binding.player1BeetleImage.visibility = View.GONE
            binding.player2BeetleImage.visibility = View.GONE
            binding.playerDice.setImageResource(R.drawable.start_dice)
            binding.computerPlayer.setImageResource(R.drawable.start_dice)
            binding.rollButton.isEnabled = true
            binding.turn.text = "Player 1's turn"
            Toast.makeText(this, "Game Reset", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_rules -> {
                RulesDialogFragment().show(supportFragmentManager, "RulesDialog")
                return true
            }
            R.id.action_author -> {
                AuthorDialogFragment().show(supportFragmentManager, "AuthorDialog")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showWinAlert(winner: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Beetle Game")
        builder.setIcon(R.drawable.beetle)
        builder.setMessage("$winner, you win! Hit Reset to start the game again.")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}