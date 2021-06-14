package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SqlDao {

    // Question
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestion(question: Question)

    @Query("SELECT * FROM `question`")
    fun getAllQuestion()

    @Query(
        "SELECT DISTINCT " +
                "    `question`.`id`," +
                "    `question`.`question`," +
                "    `question`.`option_1`," +
                "    `question`.`option_2`," +
                "    `question`.`option_3`," +
                "    `question`.`option_4`," +
                "    `question`.`answer`," +
                "    `question`.`validation`" +
                " FROM `question`" +
                " LEFT JOIN `question_tag` ON `question`.`id` = `question_tag`.`question_id`" +
                " LEFT JOIN `question_type` ON `question`.`id` = `question_type`.`question_id`" +
                " WHERE `type_id` = :typeId AND `tag_id` IN (:tagIds)"
    )
    fun getQuestionsByTypeAndTag(typeId: Int, tagIds: String)

    // question-type
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestionType(relation: QuestionType)

    // type
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertType(type: Type)

    @Query("SELECT * FROM `type`")
    fun getAllType()

    // question-tag
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestionTag(relation: QuestionTag)

    // tag
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertTag(tag: Tag)

    @Query("SELECT * FROM `tag`")
    fun getAllTag()

    // history
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertHistory(history: History)

    @Query("SELECT * FROM `history`")
    fun getAllHistory()

    @Query("SELECT * FROM `history` WHERE `id` = :history_id")
    fun getHistory(history_id: Int)

    // history-tag
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertHistoryTag(relation: HistoryTag)

    @Query("SELECT * FROM `history_tag` WHERE `history_id` = :history_id")
    fun getHistoryTag(history_id: Int)

    // history-detail
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertHistoryDetails(details: List<HistoryDetail>)

    @Query(
        "SELECT * FROM `history_detail`" +
                " LEFT JOIN `question` ON `history_detail`.`question_id` = `question`.`id`" +
                " WHERE `history_id` = :history_id"
    )
    fun getHistoryDetails(history_id: Int)
}
