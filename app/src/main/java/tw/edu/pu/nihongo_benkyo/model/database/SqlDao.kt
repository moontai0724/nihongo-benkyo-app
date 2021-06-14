package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SqlDao {

    // Question
    @Insert
    fun insertQuestion(question: Question)

    @Query("SELECT * FROM question")
    fun getQuestion()

    @Query("DELETE FROM question")
    fun deleteAllQuestion()

    // type
    @Insert
    fun insertType(type: Type)

    @Query("SELECT * FROM question")
    fun getType()

    @Query("DELETE FROM type")
    fun deleteType()

    // tag
    @Insert
    fun insertTag(tag: Tag)

    @Query("SELECT * FROM question")
    fun getTag()

    @Query("DELETE FROM tag")
    fun deleteTag()

}