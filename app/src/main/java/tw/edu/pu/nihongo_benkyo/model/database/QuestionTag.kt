package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

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
    ],
    primaryKeys = ["question_id", "tag_id"],
    indices = [Index(value = ["tag_id"])]
)
data class QuestionTag (
    @ColumnInfo(name = "question_id")
    var questionId: Int,
    @ColumnInfo(name = "tag_id")
    var tagId: Int
)