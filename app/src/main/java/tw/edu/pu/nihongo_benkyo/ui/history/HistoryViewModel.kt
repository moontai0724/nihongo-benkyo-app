package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tw.edu.pu.nihongo_benkyo.MainActivity
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.model.database.HistoryDetailAndQuestion
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo
import tw.edu.pu.nihongo_benkyo.model.database.Tag

class HistoryViewModel : ViewModel() {

    var allHistory: MutableLiveData<List<HistoryInfo>> = MutableLiveData()

    var historyDetails: MutableLiveData<List<HistoryDetailAndQuestion>> = MutableLiveData()

    fun getAllHistory() {
        GlobalScope.launch {
            allHistory.postValue(MainActivity.database.getAllHistory())
        }
    }

    fun allHistoryReplay(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_nav_history_to_nav_game)
        /*
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putStringArrayList("tags", tags)
            */
        // action_gamingSelectionFragment_to_nav_history
        // action_gamingInputFragment_to_nav_history
    }

    fun showHistoryDetail(view: View, historyInfo: HistoryInfo) {
        val bundle = Bundle()
        bundle.putSerializable("historyInfo", historyInfo)
        Navigation.findNavController(view)
            .navigate(R.id.action_allHistoryFragment_to_historyDetailFragment, bundle)
    }

    fun getAllDetail(historyId: Long) {
        GlobalScope.launch {
            historyDetails.postValue(MainActivity.database.getHistoryDetails(historyId))
        }
    }

    fun historyDetailReplay(view: View) {
        /*
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putStringArrayList("tags", tags)
            */
        Navigation.findNavController(view).navigate(R.id.action_historyDetailFragment_to_nav_game)
        // action_historyDetailFragment_to_gamingInputFragment
        // action_historyDetailFragment_to_gamingSelectionFragment
    }

    companion object {
        @JvmStatic
        @BindingAdapter("setTags")
        fun setTag(text: TextView, info: List<Tag>) {
            if (info.isNotEmpty()) {
                var str = info[0].chinese
                info.forEach {
                    if (it.chinese != str)
                        str += ", ${it.chinese}"
                }
                text.text = str
            }
        }
    }
}
