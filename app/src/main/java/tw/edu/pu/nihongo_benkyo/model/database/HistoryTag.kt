package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.*

@Entity(
    tableName = "history_tag",
    foreignKeys = [
        ForeignKey(
            entity = History::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("history_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tag_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["history_id", "tag_id"],
    indices = [Index(value = ["tag_id"])]
)
data class HistoryTag(
    @ColumnInfo(name = "history_id")
    var historyId: Long,

    @ColumnInfo(name = "tag_id")
    var tagId: Long
)