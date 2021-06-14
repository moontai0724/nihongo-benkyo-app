package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "option_1")
    var option1: String,

    @ColumnInfo(name = "option_2")
    var option2: String,

    @ColumnInfo(name = "option_3")
    var option3: String,

    @ColumnInfo(name = "option_4")
    var option4: String,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "validation")
    var validation: String
)