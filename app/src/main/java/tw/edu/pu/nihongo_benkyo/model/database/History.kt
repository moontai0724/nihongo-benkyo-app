package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "history",
    foreignKeys = [
        ForeignKey(
            entity = Type::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("type_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class History(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "type_id")
    var typeId: Int,

    @ColumnInfo(name = "time")
    var time: String,

    @ColumnInfo(name = "total_question_amount")
    var totalQuestionAmount: String,

    @ColumnInfo(name = "total_correct_amount")
    var totalCorrectAmount: String
)