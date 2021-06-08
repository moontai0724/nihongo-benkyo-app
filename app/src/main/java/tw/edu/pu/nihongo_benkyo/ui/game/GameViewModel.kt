package tw.edu.pu.nihongo_benkyo.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.edu.pu.nihongo_benkyo.json.Tag
import tw.edu.pu.nihongo_benkyo.model.Repository
import java.util.*

class GameViewModel : ViewModel() {
    var arr: MutableLiveData<List<Tag>> = MutableLiveData()
    private val repository = Repository()
    var type: String = ""
    var tags: List<String> = ArrayList()
    var single: Boolean = true
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