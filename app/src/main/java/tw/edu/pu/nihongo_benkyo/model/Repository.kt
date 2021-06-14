package tw.edu.pu.nihongo_benkyo.model

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.edu.pu.nihongo_benkyo.json.GameData
import tw.edu.pu.nihongo_benkyo.model.database.*

class Repository(context: Context) {
    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://ts1.cmrdb.cs.pu.edu.tw/~moontai0724/")
        .build()
    private var database: SqlDao = MyDatabase.getDatabase(context, GlobalScope).getDao()

    fun update(completion: (Boolean) -> Unit) {
        retrofit.create(ApiService::class.java).getAllData().enqueue(object : Callback<GameData> {
            override fun onResponse(call: Call<GameData>, response: Response<GameData>) {
                val body: GameData = response.body()!!
                GlobalScope.launch {
                    setDataIntoDatabase(body)
                }.invokeOnCompletion {
                    completion(true)
                }
            }

            override fun onFailure(call: Call<GameData>, t: Throwable) {
                t.printStackTrace()
                completion(false)
            }

        })
    }

    private suspend fun setDataIntoDatabase(body: GameData) {
        val tagIds: List<Long> =
            database.insertTags(body.tags.mapTo(ArrayList(), { property -> Tag(property) }))
        val typeIds: List<Long> =
            database.insertTypes(body.types.mapTo(ArrayList(), { property -> Type(property) }))

        for (it in body.questions) {
            val question = Question(it)
            val result = database.insertQuestion(question)
            if (result < 0)
                continue

            for (typeId in it.types)
                database.insertQuestionType(QuestionType(it.id.toInt(), typeId))
            for (tagId in it.tags)
                database.insertQuestionTag(QuestionTag(it.id.toInt(), tagId))
        }
    }
}