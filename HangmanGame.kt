import kotlin.random.Random

class HangmanGame(private val wordList: List<String>) {
    private val targetWord = wordList.random().uppercase()
    private val guessedLetters = mutableListOf<Char>()
    private var remainingAttempts = 6

    fun startGame() {
        println("🎮 Welcome to Hangman!")
        println("Guess the word. You have $remainingAttempts incorrect attempts.\n")

        while (!isGameOver()) {
            displayWord()
            print("Enter a letter: ")
            val input = readLine()?.uppercase()

            if (input.isNullOrEmpty() || input.length != 1) {
                println("❗ Please enter a single letter.\n")
                continue
            }

            val letter = input[0]

            if (letter in guessedLetters) {
                println("⚠ You've already guessed '$letter'. Try a different letter.\n")
                continue
            }

            guessedLetters.add(letter)

            if (letter in targetWord) {
                println("✅ Good guess!\n")
            } else {
                remainingAttempts--
                println("❌ Wrong guess. Attempts left: $remainingAttempts\n")
            }
        }

        if (isWordGuessed()) {
            println("🎉 You won! The word was: $targetWord")
        } else {
            println("💀 Game over! The word was: $targetWord")
        }
    }

    private fun isWordGuessed(): Boolean {
        return targetWord.all { it in guessedLetters }
    }

    private fun isGameOver(): Boolean {
        return remainingAttempts == 0 || isWordGuessed()
    }

    private fun displayWord() {
        val display = targetWord.map { if (it in guessedLetters) it else '_' }.joinToString(" ")
        println("Word: $display")
    }
}

fun main() {
    val words = listOf("kotlin", "programming", "developer", "hangman", "project")
    val game = HangmanGame(words)
    game.startGame()
}
