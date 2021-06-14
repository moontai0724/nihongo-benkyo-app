package tw.edu.pu.nihongo_benkyo.ui.game

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.ChoseQuestionItemBinding
import tw.edu.pu.nihongo_benkyo.databinding.ChoseTypeItemBinding
import tw.edu.pu.nihongo_benkyo.json.Property
import kotlin.properties.Delegates

class GameSettingAdapter(
    private val activity: Activity,
    private val viewModel: GameViewModel,
    private val single: Boolean
) :
    RecyclerView.Adapter<GameChoseViewHolder>() {

    private var dataArr: List<Property> = ArrayList()
    private var selectedPosition by Delegates.observable(-1) { _, oldPos, newPos ->
        notifyItemChanged(oldPos)
        notifyItemChanged(newPos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameChoseViewHolder {
        val questionBinding =
            ChoseQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val typeBinding =
            ChoseTypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return if (single)
            GameChoseViewHolder(activity, typeBinding)
        else
            GameChoseViewHolder(questionBinding)


    }

    override fun onBindViewHolder(holder: GameChoseViewHolder, position: Int) {
        if (holder.binding is ChoseQuestionItemBinding) {
            holder.setBind(false, dataArr[position])
            val bind = holder.binding as ChoseQuestionItemBinding
            viewModel.tags.forEach {
                if (it == dataArr[position].chinese)
                    bind.checkBox.isChecked = true
            }

            bind.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    if (!viewModel.tags.contains(buttonView.text.toString()))
                        viewModel.tags = viewModel.tags + buttonView.text.toString()
                    else
                        viewModel.tags.drop(viewModel.tags.indexOf(buttonView.text.toString()))
            }
        } else if (holder.binding is ChoseTypeItemBinding) {
            holder.setBind(selectedPosition == position, dataArr[position])
            val bind = holder.binding as ChoseTypeItemBinding
            if (viewModel.type == dataArr[position].chinese){
                Handler(Looper.getMainLooper()).postDelayed({
                    bind.radioButton.isChecked = true
                }, 0)
            }
            bind.radioButton.setOnCheckedChangeListener { buttonView, _ ->
                selectedPosition = position
                viewModel.type = buttonView.text.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataArr.size
    }

    fun setData(arr: List<Property>) {
        dataArr = arr
        notifyDataSetChanged()

    }


}

class GameChoseViewHolder :
    RecyclerView.ViewHolder {
    var binding: Any
    private lateinit var activity: Activity

    constructor(activity: Activity, typeBind: ChoseTypeItemBinding) : super(typeBind.root) {
        binding = typeBind
        this.activity = activity
    }

    constructor(questionBind: ChoseQuestionItemBinding) : super(questionBind.root) {
        binding = questionBind
    }

    fun setBind(select: Boolean, tag: Property) {
        if (binding is ChoseQuestionItemBinding) {
            val bind = binding as ChoseQuestionItemBinding
            bind.checkBox.text = tag.chinese
            bind.executePendingBindings()
        } else if (binding is ChoseTypeItemBinding) {
            val bind = binding as ChoseTypeItemBinding
            bind.radioButton.text = tag.chinese
            Handler(Looper.getMainLooper()).postDelayed({
                bind.radioButton.isChecked = select
                bind.executePendingBindings()
            }, 0)
        }
    }
}