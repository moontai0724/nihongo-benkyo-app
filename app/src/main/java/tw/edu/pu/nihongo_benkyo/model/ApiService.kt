package tw.edu.pu.nihongo_benkyo.model

import retrofit2.Call
import retrofit2.http.GET
import tw.edu.pu.nihongo_benkyo.json.GameData

interface ApiService {

    @GET("78eb40cb2c5185923b185f300e64d6b9/raw/1d1c315d81972799108547f11938f46354295513/data.json")
    fun getAllData(): Call<GameData>
}