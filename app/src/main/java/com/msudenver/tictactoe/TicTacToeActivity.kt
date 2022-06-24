package com.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s):
 * Description: App 01 - TicTacToe Activity class
 */

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TicTacToeActivity : AppCompatActivity(),View.OnClickListener {

    // TODO (suggested): maintain a reference to a TicTacToe object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictactoe)

        // TODO (suggested): get the player's name, symbol, and the first move choice from the activity's intent; use them to instantiate a TicTacToe game; decide whether the computer should play based on the first move choice

        // TODO (suggested): get a reference to the TextView "player info" area; update the TextView with the player's name and symbol

        // TODO (suggested): using a loop and button tags, update their texts and "onClick" listeners to TicTacToeActivity; remember to disable the button if it corresponds to a computer's first move
        // hint: use "findViewWithTag"

    }

    // TODO (suggested): display a Toast with a text based on the game's result
    fun showResults() {
    }

    // TODO (suggested): cast the given view as a Button; disable the button so you don't forget; get the button's tag and use it to infer the player's move coordinates; make the move and update the button's text with the player's symbol; if the game is over, show results; otherwise, have the computer play; use TitTacToe's last move and "findViewWithTag" to get a reference to the button of the computer's play; disable the button so you don't forget; update the button's text with the computer's symbol; if the game is over, show results
    override fun onClick(view: View?) {

    }


    // TODO: save the TicTacToe object using the outState Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    // TODO: restore the TicTacToe object using the savedInstanceState Bundle
    override fun onRestoreInstanceState(savedIntanceState: Bundle) {
        super.onRestoreInstanceState(savedIntanceState)
    }
}