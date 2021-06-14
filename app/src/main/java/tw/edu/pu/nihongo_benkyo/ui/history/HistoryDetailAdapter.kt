package tw.edu.pu.nihongo_benkyo.ui.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.*
import tw.edu.pu.nihongo_benkyo.model.database.HistoryDetailAndQuestion
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo

//define type 0 -> input  1-> selection
class HistoryDetailAdapter(val type:Int, val viewModel: HistoryViewModel) : RecyclerView.Adapter<HistoryDetailViewHolder>(){
    var historyDetail: List<HistoryDetailAndQuestion> = ArrayList()
    lateinit var historyDetailInfo: HistoryInfo

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
        if (position == 0){
            holder.setInfo(historyDetailInfo)
        }else{
            holder.setListData(historyDetail[position-1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            1
        else
            2
    }

    override fun getItemCount(): Int {
        return historyDetail.size + 1
    }

    fun setData(list: List<HistoryDetailAndQuestion>, info: HistoryInfo){
        historyDetail = list
        historyDetailInfo = info
    }

}
class HistoryDetailViewHolder:
    RecyclerView.ViewHolder {

    var binding: Any
    constructor(bind: HistorySingleInformationItemBinding) : super(bind.root) {
        binding = bind
    }
    constructor(bind: HistorySingleInputItemBinding) : super(bind.root) {
        binding = bind
    }
    constructor(bind: HistorySingleSelectionItemBinding) : super(bind.root) {
        binding = bind
    }

    fun setInfo(info: HistoryInfo){
        val bind = binding as HistorySingleInformationItemBinding
        bind.historyInfo = info
        bind.executePendingBindings()
    }

    fun setListData(data: HistoryDetailAndQuestion){
        if (binding is HistorySingleInputItemBinding){
            val bind = binding as HistorySingleInputItemBinding
            bind.data = data
        }else if (binding is HistorySingleSelectionItemBinding){
            val bind = binding as HistorySingleSelectionItemBinding
            bind.data = data
        }
    }
}