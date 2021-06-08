package tw.edu.pu.nihongo_benkyo.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.HistoryItemBinding

class AllHistoryAdapter() : RecyclerView.Adapter<AllHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHistoryViewHolder {
        val binding =
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllHistoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }


}

class AllHistoryViewHolder(val binding: HistoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setBind(select: Boolean) {

    }
}