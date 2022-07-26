package com.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Adam Prieto and Bishnu Bhusal
 * Description: App 01 - MainActivity class
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODOd (suggested): get a reference to the "play" button and use it to set its "onClick" listener to MainActivity
        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {

            onClick(playButton)
        }

    }

    // TODOd (suggested): get the player's name, symbol, and the first move choice; pass info to the TicTacToe activity
    override fun onClick(p0: View?) {
        val playerName = (findViewById<EditText>(R.id.playerName)).text.toString()
        val rgChoices: RadioGroup = findViewById(R.id.radioGroup)

        // Get the clicked radio button instance
        val checkedSymbol:RadioButton = findViewById(rgChoices.checkedRadioButtonId)
        // Get the response if player wants to play first
        val response: RadioGroup = findViewById(R.id.selectedResponse)
        val selectedResponse: RadioButton = findViewById(response.checkedRadioButtonId)

        val symbol = if (checkedSymbol == (findViewById<RadioButton>(R.id.btnNoughts))) {
            'O'
        } else {
            'X'
        }

        val yesButton: RadioButton = findViewById(R.id.btnYes)
        //val noButton: RadioButton = findViewById(R.id.btnNo)
        val firstMove =  (selectedResponse == yesButton)


        intent = Intent(this, TicTacToeActivity::class.java)
        intent.putExtra("playerName", playerName)
        intent.putExtra("playerSymbol",symbol)
        intent.putExtra("firstMove", firstMove)
        startActivity(intent)
        

    }

}