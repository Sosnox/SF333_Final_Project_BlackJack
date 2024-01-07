

data class GameState(
    var PlayerTotalPoint: Int = 0,
    var DealerTotalPoint: Int = 0,
    val hintText: String = "Player Turn",
    var currentTurn: Person = Person.Player,
    var hasWon: Boolean = false
) {
    fun toggleTurn() {
        currentTurn = if (currentTurn == Person.Dealer) Person.Player else Person.Dealer
    }

//    fun updateScore(playerScore: Int, dealerScore: Int) {
//        PlayerTotalPoint = playerScore
//        DealerTotalPoint = dealerScore
//    }
}

enum class Person {
    Dealer,
    Player
}

data class Card(val suit: String, val value: String , val boolean: Boolean) {
    override fun toString(): String {
        return "$value $suit $boolean"
    }
}

object Deck {
    val suits = listOf("♣", "♦", "♥", "♠")
    val readableNames = listOf(
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "J",
        "Q",
        "K",
        "Ace"
    )


    val cards: MutableList<Card> by lazy {
        val deck = mutableListOf<Card>()
        for (suit in suits) {
            for (value in readableNames+readableNames) {
                var boolean = false
                deck.add(Card(suit, value , boolean))
            }
        }
        deck.shuffle() // Shuffle the deck after creation
        deck // Return the shuffled deck
    }
}


