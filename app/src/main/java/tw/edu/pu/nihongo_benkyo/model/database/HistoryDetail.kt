package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "history_detail",
    foreignKeys = [
        ForeignKey(
            entity = History::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("history_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["history_id", "question_id"]
)
data class HistoryDetail(
    @ColumnInfo(name = "history_id")
    var historyId: Int,

    @ColumnInfo(name = "question_id")
    var questionId: Int,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "correctness")
    var correctness: Boolean
)