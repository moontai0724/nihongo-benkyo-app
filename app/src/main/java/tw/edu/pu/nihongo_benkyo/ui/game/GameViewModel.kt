package tw.edu.pu.nihongo_benkyo.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.edu.pu.nihongo_benkyo.model.Repository
import java.util.*

class GameViewModel : ViewModel() {
    var arr: MutableLiveData<List<Any>> = MutableLiveData()
    private val repository = Repository()
    fun getType(){
        repository.getType {
            arr.postValue(it)
        }
    }

    fun getTheme(){
        repository.getTag {
            arr.postValue(it)
        }
    }
}