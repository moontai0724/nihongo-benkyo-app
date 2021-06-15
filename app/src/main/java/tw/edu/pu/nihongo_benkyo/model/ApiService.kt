package tw.edu.pu.nihongo_benkyo.model

import retrofit2.Call
import retrofit2.http.GET
import tw.edu.pu.nihongo_benkyo.json.GameData

interface ApiService {

    @GET("data.json")
    fun getAllData(): Call<GameData>
}