package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    ]
)
data class HistoryTag(
    @ColumnInfo(name = "history_id")
    var id: Int,

    @ColumnInfo(name = "tag_id")
    var tid: Int
)