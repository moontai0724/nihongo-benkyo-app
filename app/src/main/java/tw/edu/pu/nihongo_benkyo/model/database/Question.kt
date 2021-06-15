package tw.edu.pu.nihongo_benkyo.model.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "option_1")
    var option1: String?,

    @ColumnInfo(name = "option_2")
    var option2: String?,

    @ColumnInfo(name = "option_3")
    var option3: String?,

    @ColumnInfo(name = "option_4")
    var option4: String?,

    @ColumnInfo(name = "answer")
    var answer: Int?,

    @ColumnInfo(name = "validation")
    var validation: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    constructor(o: tw.edu.pu.nihongo_benkyo.json.Question) : this(
        o.id,
        o.question,
        o.option_1,
        o.option_2,
        o.option_3,
        o.option_4,
        o.answer,
        o.validation
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(question)
        parcel.writeString(option1)
        parcel.writeString(option2)
        parcel.writeString(option3)
        parcel.writeString(option4)
        parcel.writeValue(answer)
        parcel.writeString(validation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}