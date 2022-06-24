package com.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s):
 * Description: App 01 - MainActivity class
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO (suggested): get a reference to the "play" button and use it to set its "onClick" listener to MainActivity
        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            intent = Intent(this,TicTacToeActivity::class.java)
            startActivity(intent)
        }

    }

    // TODO (suggested): get the player's name, symbol, and the first move choice; pass info to the TicTacToe activity
    override fun onClick(p0: View?) {

    }


}