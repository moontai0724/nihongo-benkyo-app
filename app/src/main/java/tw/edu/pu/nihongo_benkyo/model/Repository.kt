package tw.edu.pu.nihongo_benkyo.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.edu.pu.nihongo_benkyo.json.GameData
import tw.edu.pu.nihongo_benkyo.json.Question
import tw.edu.pu.nihongo_benkyo.json.Tag

class Repository {
    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://gist.githubusercontent.com/moontai0724/")
        .build()

    fun getType(completion: (List<String>) -> Unit) {
        retrofit.create(ApiService::class.java).getAllData().enqueue(object : Callback<GameData>{
            override fun onResponse(call: Call<GameData>, response: Response<GameData>) {
                completion(response.body()!!.types)
            }

            override fun onFailure(call: Call<GameData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun getTag(completion: (List<Tag>) -> Unit) {
        retrofit.create(ApiService::class.java).getAllData().enqueue(object : Callback<GameData>{
            override fun onResponse(call: Call<GameData>, response: Response<GameData>) {
                completion(response.body()!!.tags)
            }

            override fun onFailure(call: Call<GameData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun getQuestion(completion: (List<Question>) -> Unit) {
        retrofit.create(ApiService::class.java).getAllData().enqueue(object : Callback<GameData>{
            override fun onResponse(call: Call<GameData>, response: Response<GameData>) {
                completion(response.body()!!.questions)
            }

            override fun onFailure(call: Call<GameData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}