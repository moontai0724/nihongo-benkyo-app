package tw.edu.pu.nihongo_benkyo.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.edu.pu.nihongo_benkyo.json.Property
import java.util.*

class GameViewModel : ViewModel() {
    var arr: MutableLiveData<List<Property>> = MutableLiveData()
    var type: String = ""
    var tags: List<String> = ArrayList()
    var single: Boolean = true
    fun getType() {}
    fun getTheme() {}
}