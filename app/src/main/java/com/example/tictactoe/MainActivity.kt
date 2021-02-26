package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0

    /////////////   //intialize 2d array to maintain board status////////////////////////////////////

    var boardStatus = Array(4) { IntArray(4) }


    //creating a 2d array with late initialization/////////////////////////
    lateinit var board: Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initializing buttons in 2d array/////////////////
        board = arrayOf(
                arrayOf(button, button2, button3),
                arrayOf(button4, button5, button6),
                arrayOf(button7, button8, button9)
        )
        //////////////////////
        for (i: Array<Button> in board) {
            for (button: Button in i) {
                button.setOnClickListener(this)
            }
        }
        ////////// initializing fxn/////////////////
        initializeBoardStatus()


        //////  Reset button fxn////////////////////////
        resetBtn.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            this.initializeBoardStatus()
            this.updateDisplay("Player X Turn")
        }


    }

    private fun initializeBoardStatus() {
        for (i: Int in 0..2) {
            for (j: Int in 0..2) {
                boardStatus[i][j] = -1
            }
        }
        for (i: Array<Button> in board) {
            for (button: Button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
                updateValue(row = 0, col = 0, Player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, Player = PLAYER)

            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, Player = PLAYER)

            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, Player = PLAYER)

            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, Player = PLAYER)

            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, Player = PLAYER)

            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, Player = PLAYER)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, Player = PLAYER)

            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, Player = PLAYER)

            }


        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER) {
            updateDisplay("Player X Turn")
        } else {
            updateDisplay("Player O Turn")

        }
        if (TURN_COUNT == 9) {
            updateDisplay("Game Draw")
        }

        this.checkWinner()


    }

    private fun checkWinner() {
         //Horizontal rows
        for (i: Int in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X winner")
                    break
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player O winner")
                    break
                }
            }
        }
        //Vertical rows
        for (i: Int in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X winner")
                    break
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player O winner")
                    break
                }
            }
        }
        //fIRST Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                updateDisplay("Player X winner")

            }else if(boardStatus[0][0] == 0){
                updateDisplay("Player O winner")

            }
        }
        //second diagonaL
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X winner")

            }else if(boardStatus[0][2] == 0){
                updateDisplay("Player O winner")

            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if(text.contains("winner")){
            this.disableButton()
        }

    }

    private fun disableButton() {
        for (i: Array<Button> in board) {
            for (button: Button in i) {
                button.isEnabled = false
            }
        }
    }


    private fun updateValue(row: Int, col: Int, Player: Boolean) {
        val text: String = if (Player) "X" else "O"
        val value: Int = if (Player) 1 else  0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}