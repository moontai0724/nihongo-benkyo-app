package tw.edu.pu.nihongo_benkyo.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.ChoseQuestionItemBinding
import tw.edu.pu.nihongo_benkyo.json.Tag

class GameSettingAdapter : RecyclerView.Adapter<GameChoseViewHolder>() {

    private var dataArr: List<Any> = ArrayList()
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

    fun setData(arr: List<Any>) {
        dataArr = arr
        notifyDataSetChanged()
    }
}

class GameChoseViewHolder(var binding: ChoseQuestionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setBind(str: Any) {
        binding.checkBox.isChecked = false
        if(str is String){
            binding.checkBox.text = str
        }else if (str is Tag){
            binding.checkBox.text = str.chinese
        }
        binding.executePendingBindings()
    }
}