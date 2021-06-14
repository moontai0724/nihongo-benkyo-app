package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.model.database.HistoryDetailAndQuestion
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo
import tw.edu.pu.nihongo_benkyo.model.database.Tag

class HistoryViewModel : ViewModel() {

    var allHistory: MutableLiveData<List<HistoryInfo>> = MutableLiveData()

    var historyDetail: MutableLiveData<List<HistoryDetailAndQuestion>> = MutableLiveData()
    var historyDetailInfo: MutableLiveData<HistoryInfo> = MutableLiveData()

    fun allHistoryReplay(view: View){
        Navigation.findNavController(view).navigate(R.id.action_nav_history_to_nav_game)
        /*
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putStringArrayList("tags", tags)
            */
        // action_gamingSelectionFragment_to_nav_history
        // action_gamingInputFragment_to_nav_history
    }

    fun showHistoryDetail(view: View, historyId:Int){
        val bundle = Bundle()
        bundle.putInt("historyId", historyId)
        Navigation.findNavController(view).navigate(R.id.action_allHistoryFragment_to_historyDetailFragment, bundle)
    }

    fun historyDetailReplay(view: View){
        /*
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putStringArrayList("tags", tags)
            */
        Navigation.findNavController(view).navigate(R.id.action_historyDetailFragment_to_nav_game)
        // action_historyDetailFragment_to_gamingInputFragment
        // action_historyDetailFragment_to_gamingSelectionFragment
    }

    @BindingAdapter("setTags")
    fun setTag(text: TextView, info:List<Tag>){
        var str = info[0].chinese
        info.forEach {
            if (it.chinese != str)
                str += ", ${it.chinese}"
        }
        text.text = str
    }
}
