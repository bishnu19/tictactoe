package com.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Adam Prieto and Bishnu Bhusal
 * Description: App 01 - TicTacToe Model class
 */

import java.io.Serializable
import kotlin.random.Random

class TicTacToe(playerName: String, playerSymbol: Char = NOUGHTS): Serializable {

    companion object {
        const val NOUGHTS = 'O'
        const val CROSSES = 'X'
        const val BLANK   = ' '
        const val BOARD_SIZE = 3
    }

    // TODOd: define "playerName" as a property
    var playerName = playerName
        get() = field // optional
        set(value) { field = value } // optional

    // TODOd: define "playerSymbol as a property; validate "playerSymbol", making NOUGHTS as default
    var playerSymbol = if (playerSymbol != NOUGHTS && playerSymbol != CROSSES) NOUGHTS else playerSymbol
        set(value) { field = if (value != NOUGHTS && value != CROSSES) field else value }

    var lastMove = Pair(-1, -1)

    // TODOd: define a "board" member variable (private) as a 2D char array of BOARD_SIZE x BOARD_SIZE
    // initialize it with BLANKs
    var board = Array(
        BOARD_SIZE,
        { Array(
            BOARD_SIZE,
            {
                BLANK
            }
        )
        }
    )

    /**
     * If there is a row with the same symbol, return the symbol; BLANK otherwise
     * @return symbol
     */
    private fun rowWin(): Char {
        for (i in 0 until BOARD_SIZE) {
            val symbol = board[i][0]
            if (symbol == BLANK)
                continue
            var found = true
            for (j in 0 until BOARD_SIZE)
                if (symbol != board[i][j]) {
                    found = false
                    break
                }
            if (found)
                return symbol
        }
        return BLANK
    }

    /**
     * TODOd: If there is a column with the same symbol, return the symbol; BLANK otherwise
     * @return symbol
     */
    private fun colWin(): Char {
        for (j in 0 until BOARD_SIZE) {
            val symbol = board[0][j]
            if (symbol == BLANK)
                continue
            var found = true
            for (i in 0 until BOARD_SIZE)
                if (symbol != board[i][j]) {
                    found = false
                    break
                }
            if (found)
                return symbol
        }
        return BLANK
    }

    /**
     * If there is a diagonal with the same symbol, return the symbol; BLANK otherwise
     * @return symbol
     */
    private fun diagonalWin(): Char {
        var symbol = board[0][0]
        if (symbol != BLANK) {
            var found = true
            for (i in 1 until BOARD_SIZE)
                if (symbol != board[i][i]) {
                    found = false
                    break
                }
            if (found)
                return symbol
        }
        symbol = board[0][BOARD_SIZE - 1]
        if (symbol != BLANK) {
            var found = true
            for (i in 1 until BOARD_SIZE)
                if (symbol != board[i][BOARD_SIZE - i - 1]) {
                    found = false
                    break
                }
            if (found)
                return symbol
        }
        return BLANK
    }

    /**
     * TODOd: if there is a winner, return its symbol; BLANK otherwise
     * @return symbol
     */
    fun getWinner(): Char {
        var winner = rowWin()
        if (winner != BLANK)
            return winner
        winner = colWin()
        if (winner != BLANK)
            return winner
        winner = diagonalWin()
        return winner
    }

    /**
     * Return true/false if there is at least an EMPTY symbol on the board
     * @return boolean
     */
    private fun canMove(): Boolean {
        for (i in 0 until BOARD_SIZE)
            for (j in 0 until BOARD_SIZE)
                if (board[i][j] == BLANK)
                    return true
        return false
    }

    /**
     * Return true/false if the game is over
     * @return
     */
    fun isGameOver(): Boolean {
        val winner = getWinner()
        if (winner != BLANK)
            return true
        return !canMove()
    }

    /**
     * Allow the player to play a move (if the move is valid)
     * @param i row coordinate
     * @param j column coordinate
     * @return true/false depending on whether the move was valid
     */
    fun playerPlay(i: Int, j: Int): Boolean {
        if (board[i][j] == BLANK) {
            board[i][j] = playerSymbol;
            lastMove = Pair(i, j)
            return true;
        }
        return false;
    }

    fun getSymbolAt(i: Int, j: Int): Char {
        return board[i][j]
    }

    /**
     * Return the computer's symbol
     * @return
     */
    fun getComputerSymbol(): Char {
        return if (playerSymbol === NOUGHTS) CROSSES else NOUGHTS
    }

    // TODOd (required): modify this method to incorporate some (not purely random) strategy
    /**
     * Allow the computer to play a random move (no AI this time)
     * @return true/false depending on whether the move was possible
     */
    // computerPlay method with strategy implemented
    fun computerPlay(): Boolean
    {
        // If the computer can make a move, do one of the following...
        if (canMove())
        {
            // 1.) Get center square first if available.
            if (board[1][1] == BLANK)
            {
                board[1][1] = getComputerSymbol()
                lastMove = Pair(1,1)
                return true
            } // End if

            // Begin strategy (the difficult stuff)

            // 2.) If a win presents itself, take it.
            else if (board[0][0] == board[0][1] && board[0][1] == getComputerSymbol() && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][0] == board[0][2] && board[0][0] == getComputerSymbol() && board[0][1] == BLANK)
            {
                board[0][1] = getComputerSymbol()
                lastMove = Pair(0,1)
                return true
            } // End else if
            else if (board[0][1] == board[0][2] && board[0][1] == getComputerSymbol() && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if
            else if (board[1][0] == board[1][1] && board[1][0] == getComputerSymbol() && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[1][1] == board[1][2] && board[1][1] == getComputerSymbol() && board[1][0] == BLANK)
            {
                board[1][0] = getComputerSymbol()
                lastMove = Pair(1,0)
                return true
            } // End else if
            else if (board[1][0] == board[1][2] && board[1][0] == getComputerSymbol() && board[1][1] == BLANK)
            {
                board[1][1] = getComputerSymbol()
                lastMove = Pair(1,1)
                return true
            } // End else if
            else if (board[2][0] == board[2][1] && board[2][1] == getComputerSymbol() && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[2][1] == board[2][2] && board[2][1] == getComputerSymbol() && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[2][0] == board[2][2] && board[2][2] == getComputerSymbol() && board[2][1] == BLANK)
            {
                board[2][1] = getComputerSymbol()
                lastMove = Pair(2,1)
                return true
            } // End else if
            else if (board[0][0] == board[1][0] && board[1][0] == getComputerSymbol() && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[1][0] == board[2][0] && board[2][0] == getComputerSymbol() && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if
            else if (board[0][0] == board[2][0] && board[2][0] == getComputerSymbol() && board[1][0] == BLANK)
            {
                board[1][0] = getComputerSymbol()
                lastMove = Pair(1,0)
                return true
            } // End else if
            else if (board[0][1] == board[1][1] && board[1][1] == getComputerSymbol() && board[2][1] == BLANK)
            {
                board[2][1] = getComputerSymbol()
                lastMove = Pair(2,1)
                return true
            } // End else if
            else if (board[1][1] == board[2][1] && board[2][1] == getComputerSymbol() && board[0][1] == BLANK)
            {
                board[0][1] = getComputerSymbol()
                lastMove = Pair(0,1)
                return true
            } // End else if
            else if (board[0][2] == board[1][2] && board[1][2] == getComputerSymbol() && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[1][2] == board[2][2] && board[2][2] == getComputerSymbol() && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][2] == board[2][2] && board[2][2] == getComputerSymbol() && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[2][0] == board[1][1] && board[2][0] == getComputerSymbol() && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][2] == board[1][1] && board[1][1] == getComputerSymbol() && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[0][0] == board[1][1] && board[0][0] == getComputerSymbol() && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[1][1] == board[2][2] && board[1][1] == getComputerSymbol() && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if
            else if (board[1][1] == board[1][2] && board[1][1] == getComputerSymbol() && board[1][0] == BLANK)
            {
                board[1][0] = getComputerSymbol()
                lastMove = Pair(1,0)
                return true
            } // End else if
            else if (board[1][0] == board[1][1] && board[1][1] == getComputerSymbol() && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[0][1] == board[1][1] && board[1][1] == getComputerSymbol() && board[2][1] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[1][0] == board[1][1] && board[1][1] == getComputerSymbol() && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if


            // 3.)If no win is possible, make a defensive choice if applicable.
            else if (board[0][0] == board[0][1] && board[0][1] == playerSymbol && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][0] == board[0][2] && board[0][0] == playerSymbol && board[0][1] == BLANK)
            {
                board[0][1] = getComputerSymbol()
                lastMove = Pair(0,1)
                return true
            } // End else if
            else if (board[0][1] == board[0][2] && board[0][1] == playerSymbol && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if
            else if (board[1][0] == board[1][1] && board[1][0] == playerSymbol && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[1][1] == board[1][2] && board[1][1] == playerSymbol && board[1][0] == BLANK)
            {
                board[1][0] = getComputerSymbol()
                lastMove = Pair(1,0)
                return true
            } // End else if
            else if (board[1][0] == board[1][2] && board[1][0] == playerSymbol && board[1][1] == BLANK)
            {
                board[1][1] = getComputerSymbol()
                lastMove = Pair(1,1)
                return true
            } // End else if
            else if (board[2][0] == board[2][1] && board[2][1] == playerSymbol && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[2][1] == board[2][2] && board[2][1] == playerSymbol && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[2][0] == board[2][2] && board[2][2] == playerSymbol && board[2][1] == BLANK)
            {
                board[2][1] = getComputerSymbol()
                lastMove = Pair(2,1)
                return true
            } // End else if
            else if (board[0][0] == board[1][0] && board[1][0] == playerSymbol && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[1][0] == board[2][0] && board[2][0] == playerSymbol && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if
            else if (board[0][0] == board[2][0] && board[2][0] == playerSymbol && board[1][0] == BLANK)
            {
                board[1][0] = getComputerSymbol()
                lastMove = Pair(1,0)
                return true
            } // End else if
            else if (board[0][1] == board[1][1] && board[1][1] == playerSymbol && board[2][1] == BLANK)
            {
                board[2][1] = getComputerSymbol()
                lastMove = Pair(2,1)
                return true
            } // End else if
            else if (board[1][1] == board[2][1] && board[2][1] == playerSymbol && board[0][1] == BLANK)
            {
                board[0][1] = getComputerSymbol()
                lastMove = Pair(0,1)
                return true
            } // End else if
            else if (board[0][2] == board[1][2] && board[1][2] == playerSymbol && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[1][2] == board[2][2] && board[2][2] == playerSymbol && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][2] == board[2][2] && board[2][2] == playerSymbol && board[1][2] == BLANK)
            {
                board[1][2] = getComputerSymbol()
                lastMove = Pair(1,2)
                return true
            } // End else if
            else if (board[2][0] == board[1][1] && board[2][0] == playerSymbol && board[0][2] == BLANK)
            {
                board[0][2] = getComputerSymbol()
                lastMove = Pair(0,2)
                return true
            } // End else if
            else if (board[0][2] == board[1][1] && board[1][1] == playerSymbol && board[2][0] == BLANK)
            {
                board[2][0] = getComputerSymbol()
                lastMove = Pair(2,0)
                return true
            } // End else if
            else if (board[0][0] == board[1][1] && board[0][0] == playerSymbol && board[2][2] == BLANK)
            {
                board[2][2] = getComputerSymbol()
                lastMove = Pair(2,2)
                return true
            } // End else if
            else if (board[1][1] == board[2][2] && board[1][1] == playerSymbol && board[0][0] == BLANK)
            {
                board[0][0] = getComputerSymbol()
                lastMove = Pair(0,0)
                return true
            } // End else if

            // End new strategy

            // 4.) Make a random move (professor provided)
            while (true)
            {
                val i: Int = Random.nextInt(BOARD_SIZE)
                val j: Int = Random.nextInt(BOARD_SIZE)
                if (board[i][j] === BLANK)
                {
                    board[i][j] = getComputerSymbol()
                    lastMove = Pair(i, j)
                    return true
                }
            } // End while
        } // End if
        return false
    } // End computerPlay

    /**
     * Return a string representation of the board
     */
    override fun toString(): String {
        var s = ""
        for (i in 0 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                if (board[i][j] == BLANK)
                    s += " - "
                else
                    s += " " + board[i][j] + " "
                s += "\t"
            }
            s += "\n"
        }
        return s
    }
}