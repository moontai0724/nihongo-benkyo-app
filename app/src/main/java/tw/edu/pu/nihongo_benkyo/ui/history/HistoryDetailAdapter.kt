package tw.edu.pu.nihongo_benkyo.ui.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_single_input_item.view.*
import kotlinx.android.synthetic.main.history_single_selection_item.view.*
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.HistorySingleInformationItemBinding
import tw.edu.pu.nihongo_benkyo.databinding.HistorySingleInputItemBinding
import tw.edu.pu.nihongo_benkyo.databinding.HistorySingleSelectionItemBinding
import tw.edu.pu.nihongo_benkyo.model.database.HistoryDetailAndQuestion
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo
import java.lang.Integer.parseInt

// define type 0 -> input  1 -> selection
class HistoryDetailAdapter(
    val type: Long,
    val viewModel: HistoryViewModel,
    var historyInfo: HistoryInfo
) : RecyclerView.Adapter<HistoryDetailViewHolder>() {
    var historyDetails: List<HistoryDetailAndQuestion> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDetailViewHolder {
        val info = HistorySingleInformationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val input = HistorySingleInputItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val selection = HistorySingleSelectionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        info.viewModel = viewModel

        return if (viewType == 1)
            HistoryDetailViewHolder(info)
        else {
            when (type.toInt()) {
                1 -> HistoryDetailViewHolder(selection)
                2 -> HistoryDetailViewHolder(input)
                3 -> HistoryDetailViewHolder(input)
                else -> HistoryDetailViewHolder(info) //暫時不可能
            }
        }
    }

    override fun onBindViewHolder(holder: HistoryDetailViewHolder, position: Int) {
        if (position == 0) {
            holder.setInfo(this.historyInfo)
        } else {
            holder.setListData(historyDetails[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1 else 2
    }

    override fun getItemCount(): Int {
        return historyDetails.size + 1
    }

    fun setData(list: List<HistoryDetailAndQuestion>) {
        historyDetails = list
        notifyDataSetChanged()
    }
}

class HistoryDetailViewHolder : RecyclerView.ViewHolder {
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

    fun setInfo(info: HistoryInfo) {
        val bind = binding as HistorySingleInformationItemBinding
        bind.historyInfo = info
        bind.executePendingBindings()
    }

    fun setListData(data: HistoryDetailAndQuestion) {
        if (binding is HistorySingleInputItemBinding) {
            val bind = binding as HistorySingleInputItemBinding
            bind.data = data
            bind.root.reply.setBackgroundColor(
                ContextCompat.getColor(
                    bind.root.context,
                    if (data.detail?.correctness!!) R.color.green_500 else R.color.pink_500
                )
            )
            bind.executePendingBindings()
        } else if (binding is HistorySingleSelectionItemBinding) {
            val bind = binding as HistorySingleSelectionItemBinding
            bind.data = data
            bind.executePendingBindings()

            for (i in arrayOf(1, 2, 3, 4))
                setColor(bind, i, R.color.white)

            setColor(bind, data.question?.answer!!, R.color.green_500)
            if (!data.detail?.correctness!!)
                setColor(bind, parseInt(data.detail?.answer!!), R.color.pink_500)
        }
    }

    private fun setColor(binding: HistorySingleSelectionItemBinding, index: Int, color: Int) {
        val target = when (index) {
            1 -> binding.root.option1
            2 -> binding.root.option2
            3 -> binding.root.option3
            4 -> binding.root.option4
            else -> null
        }
        target?.setBackgroundColor(ContextCompat.getColor(binding.root.context, color))
    }
}