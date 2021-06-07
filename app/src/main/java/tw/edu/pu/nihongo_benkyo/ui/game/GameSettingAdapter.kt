package tw.edu.pu.nihongo_benkyo.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.ChoseQuestionItemBinding

class GameSettingAdapter : RecyclerView.Adapter<GameChoseViewHolder>() {

    private var dataArr: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameChoseViewHolder {
        val binding =
            ChoseQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameChoseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameChoseViewHolder, position: Int) {
        holder.setBind(dataArr[position])
    }

    override fun getItemCount(): Int {
        return dataArr.size
    }

    fun setData(arr: ArrayList<String>) {
        dataArr = arr
        notifyDataSetChanged()
    }
}

class GameChoseViewHolder(var binding: ChoseQuestionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setBind(str: String) {
        binding.checkBox.text = str
        binding.executePendingBindings()
    }
}