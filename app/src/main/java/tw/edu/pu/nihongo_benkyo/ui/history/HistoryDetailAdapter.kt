package tw.edu.pu.nihongo_benkyo.ui.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.*

//define type 0 -> input  1-> selection
class HistoryDetailAdapter(val type:Int, val viewModel: HistoryViewModel) : RecyclerView.Adapter<HistoryDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDetailViewHolder {
        val info = HistorySingleInformationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val input =  HistorySingleInputItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val selection = HistorySingleSelectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        info.viewModel = viewModel
        return if (viewType == 1)
            HistoryDetailViewHolder(info)
        else {
            when (type) {
                0 -> HistoryDetailViewHolder(input)
                1 -> HistoryDetailViewHolder(selection)
                else -> HistoryDetailViewHolder(info) //暫時不可能
            }
        }
    }

    override fun onBindViewHolder(holder: HistoryDetailViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            1
        else
            2
    }

    override fun getItemCount(): Int {
        return 10
    }
}
class HistoryDetailViewHolder:
    RecyclerView.ViewHolder {

    val binding: Any
    constructor(bind: HistorySingleInformationItemBinding) : super(bind.root) {
        binding = bind
    }
    constructor(bind: HistorySingleInputItemBinding) : super(bind.root) {
        binding = bind
    }
    constructor(bind: HistorySingleSelectionItemBinding) : super(bind.root) {
        binding = bind
    }
}