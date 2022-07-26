package com.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Adam Prieto and Bishnu Bhusal
 * Description: App 01 - TicTacToe Activity class
 */

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TicTacToeActivity : AppCompatActivity(), View.OnClickListener
{

    // TODOd (suggested): maintain a reference to a TicTacToe object
    var ticTacToe: TicTacToe? = null
    private var name: String? = null
    private var symbol: Char? = null
    private var firstMove: Boolean? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictactoe)

        /* TODOd (suggested): get the player's name, symbol, and the first move choice from the
            activity's intent;
            use them to instantiate a TicTacToe game; decide whether the computer
             should play based on the first move choice
         */

        name = intent.getStringExtra("playerName").toString()
        symbol = intent.getCharExtra("playerSymbol", 0.toChar())
        firstMove = intent.getBooleanExtra("firstMove", true)
        ticTacToe = TicTacToe(name!!, symbol!!)



        // TODOd (suggested): get a reference to the TextView "player info" area; update the TextView with the player's name and symbol
        val textView: TextView = findViewById(R.id.txtView)
        textView.text = "Player $name is using the symbol $symbol"

        /* TODOd (suggested): using a loop and button tags, update their texts and "onClick"
            listeners to TicTacToeActivity; remember to disable the button if it corresponds to a
             computer's first move
         */
        // hint: use "findViewWithTag"


        // At this point, we've hard coded all of the buttons for simplicity sake,
        // but if we have time we can soft code them later.
        val centerButton: Button = findViewById(R.id.btnFive)
        centerButton.setOnClickListener {
            onClick(centerButton)

        }

        val button1: Button = findViewById(R.id.btnOne)
        button1.setOnClickListener {
            onClick(button1)

        }

        val button2: Button = findViewById(R.id.btnTwo)
        button2.setOnClickListener {
            onClick(button2)

        }

        val button3: Button = findViewById(R.id.btnThree)
        button3.setOnClickListener {
            onClick(button3)

        }

        val button4: Button = findViewById(R.id.btnFour)
        button4.setOnClickListener {
            onClick(button4)

        }

        val button6: Button = findViewById(R.id.btnSix)
        button6.setOnClickListener {
            onClick(button6)

        }

        val button7: Button = findViewById(R.id.btnSeven)
        button7.setOnClickListener {
            onClick(button7)

        }

        val button8: Button = findViewById(R.id.btnEight)
        button8.setOnClickListener {
            onClick(button8)

        }

        val button9: Button = findViewById(R.id.btnNine)
        button9.setOnClickListener {
            onClick(button9)

        }

        // To be tested
        if (!firstMove!!)
        {
            ticTacToe?.computerPlay()
            ticTacToe?.lastMove

            centerButton.text = ticTacToe?.getComputerSymbol().toString()
            centerButton.isEnabled = false
        } // End if
    } // End onCreate

    // TODOd (suggested): display a Toast with a text based on the game's result
    private fun showResults()
    {
        if (ticTacToe?.getWinner() == ticTacToe?.playerSymbol)
        {
            Toast.makeText(this, "You won; congratulations!!!", Toast.LENGTH_SHORT).show()
            return
        }


        else if (ticTacToe?.getWinner() == ticTacToe?.getComputerSymbol()){
            Toast.makeText(
                this,
                "I won; computers are superior!!!",
                Toast.LENGTH_SHORT
                          )
                .show()
            return
        }
        else
        {
            Toast.makeText(this, "Tie!!!", Toast.LENGTH_LONG).show()
        } // End else
    } // End showResults

    /* TODOd (suggested): cast the given view as a Button; disable the button so you don't forget;
         get the button's tag and use it to infer the player's move coordinates; make the move and
          update the button's text with the player's symbol; if the game is over, show results;
           otherwise, have the computer play; use TitTacToe's last move and "findViewWithTag" to get
            a reference to the button of the computer's play; disable the button so you don't
            forget; update the button's text with the computer's symbol; if the game is over,
            show results
     */
    override fun onClick(view: View?)
    {
        // Get button reference
        val button: Button? = view?.let { findViewById(it.id) }

        // Update player move if applicable
        if (button?.text == "")
        {
            button.text = symbol.toString()
            ticTacToe?.playerPlay(button.tag.toString()[0].digitToInt(),
                                  button.tag.toString()[1].digitToInt())

            println(ticTacToe?.toString())
        }

        button?.isEnabled = false
        if (ticTacToe?.isGameOver()!!)
        {
            showResults()
            return
        }
        else
        {
            makeComputerMove()
        }
    } // End onClick


    private fun makeComputerMove()
    {

        val wholeLayout: TableLayout = findViewById(R.id.whole_layout)
        ticTacToe?.computerPlay()
        val button = wholeLayout.findViewWithTag<Button>("${ticTacToe?.lastMove?.first}${ticTacToe?.lastMove?.second}")

        button.text=
            ticTacToe?.lastMove?.first?.let {
                ticTacToe?.lastMove?.second?.let { it1 ->
                    ticTacToe?.getSymbolAt(it, it1)
                        .toString()
                }
            }
        button.isEnabled = false

        if (ticTacToe?.isGameOver()!!)
        {
            showResults()
            return
        }
    }

    // TODO: save the TicTacToe object using the outState Bundle
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
    }

    // TODO: restore the TicTacToe object using the savedInstanceState Bundle
    override fun onRestoreInstanceState(savedIntanceState: Bundle)
    {
        super.onRestoreInstanceState(savedIntanceState)
    }
}