package tw.edu.pu.nihongo_benkyo.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.HistoryItemBinding
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo

class AllHistoryAdapter(val viewModel: HistoryViewModel) :
    RecyclerView.Adapter<AllHistoryViewHolder>() {
    private var allHistory: List<HistoryInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHistoryViewHolder {
        val binding =
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = viewModel
        return AllHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllHistoryViewHolder, position: Int) {
        holder.setBind(allHistory[position])
    }

    override fun getItemCount(): Int {
        return allHistory.size
    }

    fun setHistory(history: List<HistoryInfo>) {
        allHistory = history
        notifyDataSetChanged()
    }

}

class AllHistoryViewHolder(val binding: HistoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setBind(data: HistoryInfo) {
        binding.historyData = data
        binding.executePendingBindings()
    }
}