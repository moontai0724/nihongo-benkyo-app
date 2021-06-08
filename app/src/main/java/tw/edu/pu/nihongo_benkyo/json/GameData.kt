package tw.edu.pu.nihongo_benkyo.json

data class GameData (
    val types: List<Tag>,
    val tags: List<Tag>,
    val questions: List<Question>
)

data class Question (
    val id: Long,
    val type: List<String>,
    val tags: List<String>,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: Long? = null,
    val validation: String
)

data class Tag (
    val english: String,
    val chinese: String
)
