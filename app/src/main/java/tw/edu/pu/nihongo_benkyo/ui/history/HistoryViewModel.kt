package tw.edu.pu.nihongo_benkyo.ui.history

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import tw.edu.pu.nihongo_benkyo.R

class HistoryViewModel : ViewModel() {

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

    fun showHistoryDetail(view: View){
        Navigation.findNavController(view).navigate(R.id.action_allHistoryFragment_to_historyDetailFragment)
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
}
