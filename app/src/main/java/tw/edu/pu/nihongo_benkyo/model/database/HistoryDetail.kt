package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.*

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
    primaryKeys = ["history_id", "question_id"],
    indices = [Index(value = ["question_id"])]
)
data class HistoryDetail(
    @ColumnInfo(name = "history_id")
    var historyId: Long,

    @ColumnInfo(name = "question_id")
    var questionId: Long,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "correctness")
    var correctness: Boolean
)

class HistoryDetailAndQuestion {
    @Embedded
    var detail: HistoryDetail? = null

    @Relation(parentColumn =  "question_id", entityColumn = "id")
    var question: Question? = null
}