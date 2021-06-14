package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "question_type",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question_id"),
            onDelete = CASCADE
        ),
        ForeignKey(entity = Type::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("type_id"),
            onDelete = CASCADE)
    ],
    primaryKeys = ["question_id", "type_id"]
)
data class QuestionType (
    @ColumnInfo(name = "question_id")
    var questionId: Int,

    @ColumnInfo(name = "type_id")
    var typeId: Int
)