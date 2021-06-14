package tw.edu.pu.nihongo_benkyo.json

data class GameData (
    val types: List<Property>,
    val tags: List<Property>,
    val questions: List<Question>
)

data class Question (
    val id: Long,
    val types: List<Int>,
    val tags: List<Int>,
    val question: String,
    val option_1: String? = null,
    val option_2: String? = null,
    val option_3: String? = null,
    val option_4: String? = null,
    val answer: Int? = null,
    val validation: String
)

data class Property (
    val id: Long,
    val english: String,
    val chinese: String
)
