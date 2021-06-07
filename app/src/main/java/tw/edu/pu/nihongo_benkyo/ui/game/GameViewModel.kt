package tw.edu.pu.nihongo_benkyo.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var arr: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun setData(data: ArrayList<String>){
        arr.postValue(data)
    }
}