package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "history_detail",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HistoryDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "question_id")
    var qid: Int,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "correctness")
    var correct: Boolean
)