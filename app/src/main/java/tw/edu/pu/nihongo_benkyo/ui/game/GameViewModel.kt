package tw.edu.pu.nihongo_benkyo.ui.game

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tw.edu.pu.nihongo_benkyo.MainActivity
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.model.database.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

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
    var currentQuestion: MutableLiveData<Question?> = MutableLiveData()
    lateinit var history: History
    var historyDetail: List<HistoryDetail> = ArrayList()
    var correctCount = 0

    fun noQuestionPop(context: Context, popUp:()->Unit){
        val alert = AlertDialog.Builder(context)
            .setTitle("注意")
            .setMessage("此內容的作答方式暫時沒有題目，請更換選項後重試")
            .setPositiveButton("確定") { _, _ ->
                popUp()
            }.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
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

    fun getQuestion(type: Long, tags: LongArray) {
        GlobalScope.launch {
            val allQuestion = MainActivity.database.getQuestionsByTypeAndTag(type, tags.toList())
            Log.d("GAME", "getQuestion: $allQuestion")
            questions.postValue(allQuestion)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initializeQuestion(type: Long, tags: LongArray){
        Log.d("GAME", "initializeQuestion: ${tags.toList()}")
        index = 0
        correctCount = 0
        historyDetail = ArrayList()
        answerInput.postValue("")
        val ts = Timestamp(Date().time)
        val time = LocalDateTime.ofInstant(ts.toInstant(), ZoneId.of("GMT+08:00"))
        history = History(
            null,
            type,
            time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "0",
            "0"
        )
        GlobalScope.launch {
            Log.d("GAME", "initializeQuestion: before history $history")
            history.id = MainActivity.database.insertHistory(history)
            Log.d("GAME", "initializeQuestion: history $history")
            tags.forEach {
                MainActivity.database.insertHistoryTag(HistoryTag(history.id!!, it))
            }
        }
    }

    fun settingComplete(view: View) {
        val bundle = Bundle()
        bundle.putLong("type", type)
        val longArrayTags = LongArray(selectTag.size, selectTag::get)
        bundle.putLongArray("tags", longArrayTags)
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
        val correct = currentQuestion.value?.answer == select
        historyDetail = historyDetail + HistoryDetail(
            history.id!!,
            currentQuestion.value!!.id,
            select.toString(),
            correct
        )
        if (correct)
            correctCount++
        if (++index >= questions.value!!.size) {
            history.totalCorrectAmount = correctCount.toString()
            history.totalQuestionAmount = historyDetail.size.toString()
            GlobalScope.launch {
                MainActivity.database.updateHistory(history)
                MainActivity.database.insertHistoryDetails(historyDetail)
            }
            Navigation.findNavController(view)
                .navigate(R.id.action_gamingSelectionFragment_to_nav_history)
        } else {
            currentQuestion.postValue(questions.value!![index])
        }
    }

    fun inputBtnClick(view: View) {
        val correct = currentQuestion.value?.validation == answerInput.value
        historyDetail = historyDetail + HistoryDetail(
            history.id!!,
            currentQuestion.value!!.id,
            answerInput.value!!,
            correct
        )
        if (correct)
            correctCount++
        if (++index >= questions.value!!.size) {
            history.totalCorrectAmount = correctCount.toString()
            history.totalQuestionAmount = historyDetail.size.toString()
            GlobalScope.launch {
                MainActivity.database.updateHistory(history)
                MainActivity.database.insertHistoryDetails(historyDetail)
            }
            Navigation.findNavController(view)
                .navigate(R.id.action_gamingInputFragment_to_nav_history)
        } else {
            currentQuestion.postValue(questions.value!![index])
        }
    }
}