package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tw.edu.pu.nihongo_benkyo.json.Property

@Entity(tableName = "tag")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "english")
    var english: String,

    @ColumnInfo(name = "chinese")
    var chinese: String
) {
    constructor(o: Property) : this(o.id, o.english, o.chinese)
}