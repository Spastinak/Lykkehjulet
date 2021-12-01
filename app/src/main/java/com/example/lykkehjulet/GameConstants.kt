package com.example.lykkehjulet

object GameConstants {
    val words = listOf(
        "Test-word",
        "Denmark",
        "Norway",
        "This-is-dumb"
        // TODO add more words to the game
    )
}

enum class Category(val words: List<String>){
    Countries(listOf("Denmark", "Norway")),
    Bodyparts(listOf("Head","Arm"))
}

//COUNTRIES