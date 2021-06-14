package tw.edu.pu.nihongo_benkyo.ui.game

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.json.Tag
import tw.edu.pu.nihongo_benkyo.model.Repository
import java.util.*

class GameViewModel : ViewModel() {
    var arr: MutableLiveData<List<Tag>> = MutableLiveData()

    private val repository = Repository()
    var type: String = ""
    var tags: List<String> = ArrayList()
    var single: Boolean = true
    fun getType() {
        repository.getType {
            arr.postValue(it)
        }
    }

    fun getTheme() {
        repository.getTag {
            arr.postValue(it)
        }
    }

    fun selectionBtnClick(view: View){
        // if 問題做完了
        Navigation.findNavController(view).navigate(R.id.action_gamingSelectionFragment_to_nav_history)
        // else 換 問題
    }

    fun inputBtnClick(view: View){
        // if 問題做完了
        Navigation.findNavController(view).navigate(R.id.action_gamingInputFragment_to_nav_history)
        // else 換 問題
    }
}