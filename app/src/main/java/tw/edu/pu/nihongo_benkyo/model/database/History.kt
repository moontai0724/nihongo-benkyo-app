package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.*

@Entity(
    tableName = "history",
    foreignKeys = [
        ForeignKey(
            entity = Type::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("type_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["type_id"])]
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

class HistoryInfo {
    @Embedded
    var history: History? = null

    @Relation(parentColumn = "type_id", entityColumn = "id")
    var type: Type? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(value = HistoryTag::class, parentColumn = "history_id", entityColumn = "tag_id")
    )
    var tags: List<Tag>? = null
}