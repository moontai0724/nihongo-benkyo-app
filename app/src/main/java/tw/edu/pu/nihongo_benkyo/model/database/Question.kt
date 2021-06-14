package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tw.edu.pu.nihongo_benkyo.json.Question

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "option_1")
    var option1: String?,

    @ColumnInfo(name = "option_2")
    var option2: String?,

    @ColumnInfo(name = "option_3")
    var option3: String?,

    @ColumnInfo(name = "option_4")
    var option4: String?,

    @ColumnInfo(name = "answer")
    var answer: Int?,

    @ColumnInfo(name = "validation")
    var validation: String?
) {
    constructor(o: Question) : this(
        o.id,
        o.question,
        o.option_1,
        o.option_2,
        o.option_3,
        o.option_4,
        o.answer,
        o.validation
    )
}