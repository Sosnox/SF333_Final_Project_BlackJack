package com.example.tictactoe

import Card
import GameState
import Person
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())
    var PointPlayer = 0
    var PointDealer = 0
    var hasWon = false

    fun setUpBoard(
        turn: Person,
        cards: MutableList<Card>,
        playerHand: MutableList<Card>,
        dealerHand: MutableList<Card>,
    ) {

        playerHand.add(cards[0])
        dealerHand.add(cards[1])
        playerHand.add(cards[2])
        dealerHand.add(cards[3])

        PointPlayer = calculateHandValue(playerHand)
        PointDealer = calculateHandValue(dealerHand)
        print(PointPlayer)

        Deck.cards.shuffle()

    }

    fun DealCard(playerHand: MutableList<Card>, cards: MutableList<Card>){
        playerHand.add(cards[0])
        PointPlayer = calculateHandValue(playerHand)

        Deck.cards.shuffle()
    }

    fun handlePlayAgain(playerHand: MutableList<Card>, dealerHand: MutableList<Card> , cards: MutableList<Card>) {
        playerHand.clear()
        dealerHand.clear()

        hasWon = false

        playerHand.add(cards[0])
        dealerHand.add(cards[1])
        playerHand.add(cards[2])
        dealerHand.add(cards[3])

        PointPlayer = calculateHandValue(playerHand)
        PointDealer = calculateHandValue(dealerHand)

        Deck.cards.shuffle()

    }

    fun stand(playerHand : MutableList<Card> , dealerHand : MutableList<Card> , cards : MutableList<Card>){
        var dealerScore = calculateHandValue(dealerHand)
        while (dealerScore < 17){
            dealerHand.add(cards[0])
            Deck.cards.shuffle()
            dealerScore = calculateHandValue(dealerHand)
        }

        var result = checkWin(playerHand,dealerHand ,cards)
        println(result +"Result Na")
    }
    fun checkWin(playerHand: MutableList<Card>, dealerHand: MutableList<Card>, cards: MutableList<Card>): String {
        var playerScore = calculateHandValue(playerHand)
        var dealerScore = calculateHandValue(dealerHand)
        PointPlayer = playerScore
        PointDealer = dealerScore


        when {
            playerScore == 21 && playerHand.size == 2 -> {
                hasWon = true
                return "Player wins with Blackjack!"
            }
            playerScore <= 21 && (playerScore > dealerScore || dealerScore > 21) -> {
                hasWon = true
                return "Player wins!"
            }
            dealerScore <= 21 && (dealerScore > playerScore || playerScore > 21) -> {
                hasWon = true
                return "Dealer wins!"
            }
            else -> "It's a draw!"
        }
        return "No winner yet"
    }

    fun calculateHandValue(hand: List<Card>): Int {
        var sum = 0
        var hasAce = false

        for (card in hand) {
            when (card.value) {
                "2", "3", "4", "5", "6", "7", "8", "9", "10" -> sum += card.value.toInt()
                "J", "Q", "K" -> sum += 10
                "Ace" -> hasAce = true
            }
        }

        if (hasAce && sum + 11 <= 21) {
            sum += 11 // Ace as 11 if it doesn't cause bust
        } else if (hasAce) {
            sum += 1 // Ace as 1 if 11 would cause bust
        }
        return sum
    }
}
