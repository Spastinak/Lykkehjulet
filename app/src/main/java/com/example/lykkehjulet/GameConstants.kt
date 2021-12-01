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
    Countries(listOf("Denmark", "Norway", "American-Samoa", "Antigua-and-Barbuda", "Afghanistan", "Australia", "Brazil", "Cuba","France","Ireland","Japan","Italy","Peru","Thailand")),
    Bodyparts(listOf("Skull","Brain","Face","Throat", "Heart", "Lung","Upper-abdomen", "Lower-abdomen", "Liver", "Kidney", "Stomach", "Bowel", "Gallbladder", "Adrenal-gland","Crena-ani", "Appendix"))
}

//COUNTRIES