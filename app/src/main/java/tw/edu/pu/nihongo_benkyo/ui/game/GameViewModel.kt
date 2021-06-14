package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tw.edu.pu.nihongo_benkyo.MainActivity
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.model.database.Question
import tw.edu.pu.nihongo_benkyo.model.database.Tag
import tw.edu.pu.nihongo_benkyo.model.database.Type

class GameViewModel : ViewModel() {
    // game set
    var types: MutableLiveData<List<Type>> = MutableLiveData()
    var tags: MutableLiveData<List<Tag>> = MutableLiveData()
    var answerInput: MutableLiveData<String> = MutableLiveData()
    var single: MutableLiveData<Boolean> = MutableLiveData()
    var type: Long = 0
    var selectTag: List<Long> = ArrayList()

    // gaming
    private var index = 0
    var questions: MutableLiveData<List<Question>> = MutableLiveData()
    var currentQuestion: MutableLiveData<Question> = MutableLiveData()

    init {
        index = 0
    }

    fun getTags() {
        GlobalScope.launch {
            tags.postValue(MainActivity.database.getAllTag())
        }
    }

    fun getType() {
        GlobalScope.launch {
            types.postValue(MainActivity.database.getAllType())
        }
    }

    fun setting(single: Boolean) {
        this.single.postValue(single)
    }

    fun getQuestion(type: Long, tags: String) {
        GlobalScope.launch {
            questions.postValue(MainActivity.database.getQuestionsByTypeAndTag(type, tags))
        }
    }

    fun settingComplete(view: View) {
        var str = selectTag[0].toString()
        selectTag.forEach {
            if (str != it.toString())
                str += ", $it"
        }

        val bundle = Bundle()
        bundle.putLong("type", type)
        bundle.putString("tags", str)
        if (selectTag.isNotEmpty()) {
            when (type.toInt()) {
                1 -> Navigation.findNavController(view)
                    .navigate(R.id.action_nav_game_to_gamingSelectionFragment, bundle)
                0 -> Toast.makeText(view.context, "有選擇沒選到喔", Toast.LENGTH_LONG).show()
                else -> Navigation.findNavController(view)
                    .navigate(R.id.action_nav_game_to_gamingInputFragment, bundle)
            }
        } else
            Toast.makeText(view.context, "有選擇沒選到喔", Toast.LENGTH_LONG).show()
    }

    fun selectionBtnClick(view: View, select: Int) {
        // if 問題做完了
        index++
        if (index >= questions.value!!.size - 1) {
            //利用select判斷對錯，新增歷史紀錄
            Navigation.findNavController(view)
                .navigate(R.id.action_gamingSelectionFragment_to_nav_history)
        } else
            currentQuestion.postValue(questions.value!![index])

    }

    fun inputBtnClick(view: View) {
        index++
        if (index >= questions.value!!.size - 1) {
            //利用answerInput判斷對錯，新增歷史紀錄
            Navigation.findNavController(view)
                .navigate(R.id.action_gamingInputFragment_to_nav_history)
        } else
            currentQuestion.postValue(questions.value!![index])
    }
}