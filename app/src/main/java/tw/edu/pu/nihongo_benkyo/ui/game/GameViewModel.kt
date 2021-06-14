package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.model.database.Type
import tw.edu.pu.nihongo_benkyo.model.database.Tag
import tw.edu.pu.nihongo_benkyo.model.Repository
import tw.edu.pu.nihongo_benkyo.model.database.Question
import kotlin.collections.ArrayList

class GameViewModel : ViewModel() {
    private val repository = Repository()

    // game set
    var tags: MutableLiveData<List<Tag>> = MutableLiveData()
    var types: MutableLiveData<List<Type>> = MutableLiveData()
    var answerInput: MutableLiveData<String> = MutableLiveData()
    var single: MutableLiveData<Boolean> = MutableLiveData()
    var type: String = ""
    var selectTag: List<String> = ArrayList()

    // gaming
    var index = 0
    var questions:List<Question> = ArrayList()
    var currentQuestion: MutableLiveData<Question> = MutableLiveData()

    init {
        index = 0
    }

    fun setting(view: View, single:Boolean){
        this.single.postValue(single)
    }

    fun settingComplete(view: View){
        val bundle = Bundle()
        bundle.putString("type",type)
        bundle.putStringArrayList("tags", selectTag as java.util.ArrayList<String>)
        if (selectTag.isNotEmpty()){
            when(type){
                "選擇題" -> Navigation.findNavController(view).navigate(R.id.action_nav_game_to_gamingSelectionFragment, bundle)
                "" -> Toast.makeText(view.context, "有選擇沒選到喔", Toast.LENGTH_LONG).show()
                else -> Navigation.findNavController(view).navigate(R.id.action_nav_game_to_gamingInputFragment, bundle)
            }
        }else
            Toast.makeText(view.context, "有選擇沒選到喔", Toast.LENGTH_LONG).show()
    }

    fun selectionBtnClick(view: View, select:Int){
        // if 問題做完了
        index ++
        if (index >= questions.size-1){
            //利用select判斷對錯，新增歷史紀錄
            Navigation.findNavController(view).navigate(R.id.action_gamingSelectionFragment_to_nav_history)
        }else
            currentQuestion.postValue(questions[index])

    }

    fun inputBtnClick(view: View){
        index ++
        if (index >= questions.size-1){
            //利用answerInput判斷對錯，新增歷史紀錄
            Navigation.findNavController(view).navigate(R.id.action_gamingInputFragment_to_nav_history)
        }else
            currentQuestion.postValue(questions[index])
    }
}