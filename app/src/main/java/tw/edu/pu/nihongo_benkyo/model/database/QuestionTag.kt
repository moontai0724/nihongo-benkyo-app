package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "question_tag",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Tag::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tag_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionTag (
    @ColumnInfo(name = "question_id")
    var qid: Int,
    @ColumnInfo(name = "tag_id")
    var tid: Int
)