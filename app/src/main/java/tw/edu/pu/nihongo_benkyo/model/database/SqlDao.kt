package tw.edu.pu.nihongo_benkyo.model.database

import androidx.room.*

@Dao
interface SqlDao {

    // Question
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestion(question: Question): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestions(question: List<Question>): List<Long>

    @Query("SELECT * FROM `question`")
    suspend fun getAllQuestion(): List<Question>

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
    suspend fun getQuestionsByTypeAndTag(typeId: Int, tagIds: String): List<Question>

    // question-type
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuestionType(relation: QuestionType): Long

    // type
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertType(type: Type): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTypes(type: List<Type>): List<Long>

    @Query("SELECT * FROM `type`")
    suspend fun getAllType(): List<Type>

    // question-tag
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuestionTag(relation: QuestionTag): Long

    // tag
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTag(tag: Tag): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTags(tag: List<Tag>): List<Long>

    @Query("SELECT * FROM `tag`")
    suspend fun getAllTag(): List<Tag>

    // history
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertHistory(history: History): Long

    @Query("SELECT * FROM `history`")
    suspend fun getAllHistory(): List<History>

    @Transaction
    @Query("SELECT * FROM `history` WHERE `id` = :history_id")
    suspend fun getHistory(history_id: Int): HistoryInfo

    // history-detail
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertHistoryDetails(details: List<HistoryDetail>): List<Long>

    @Transaction
    @Query("SELECT * FROM `history_detail` WHERE `history_id` = :history_id")
    suspend fun getHistoryDetails(history_id: Int): List<HistoryDetailAndQuestion>
}
