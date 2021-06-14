package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index

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
    primaryKeys = ["question_id", "type_id"],
    indices = [Index(value = ["type_id"])]
)
data class QuestionType (
    @ColumnInfo(name = "question_id")
    var questionId: Int,
    @ColumnInfo(name = "type_id")
    var typeId: Int
)