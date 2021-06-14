package tw.edu.pu.nihongo_benkyo.ui.game

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.nihongo_benkyo.databinding.ChoseQuestionItemBinding
import tw.edu.pu.nihongo_benkyo.databinding.ChoseTypeItemBinding
import tw.edu.pu.nihongo_benkyo.model.database.Tag
import tw.edu.pu.nihongo_benkyo.model.database.Type
import kotlin.properties.Delegates

class GameSettingAdapter(
    private val activity: Activity,
    private val viewModel: GameViewModel,
    private val single: Boolean
) :
    RecyclerView.Adapter<GameChoseViewHolder>() {

    private var tagArr: List<Tag> = ArrayList()
    private var typeArr: List<Type> = ArrayList()

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
            holder.setTag(tagArr[position])
            val bind = holder.binding as ChoseQuestionItemBinding
            viewModel.selectTag.forEach {
                if (it == tagArr[position].chinese)
                    bind.checkBox.isChecked = true
            }
            bind.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    if (!viewModel.selectTag.contains(buttonView.text.toString()))
                        viewModel.selectTag = viewModel.selectTag + buttonView.text.toString()
                    else
                        viewModel.selectTag.drop(viewModel.selectTag.indexOf(buttonView.text.toString()))
            }
        } else if (holder.binding is ChoseTypeItemBinding) {
            holder.setType(selectedPosition == position, typeArr[position])
            val bind = holder.binding as ChoseTypeItemBinding
            if (viewModel.type == typeArr[position].chinese) {
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
        return if (single)
            typeArr.size
        else
            tagArr.size
    }

    fun setTagData(arr: List<Tag>) {
        tagArr = arr
        notifyDataSetChanged()
    }

    fun setTypeData(arr: List<Type>) {
        typeArr = arr
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

    fun setTag(tag: Tag) {
        val bind = binding as ChoseQuestionItemBinding
        bind.checkBox.text = tag.chinese
        bind.executePendingBindings()
    }

    fun setType(select: Boolean, type: Type) {
        val bind = binding as ChoseTypeItemBinding
        bind.radioButton.text = type.chinese
        Handler(Looper.getMainLooper()).postDelayed({
            bind.radioButton.isChecked = select
            bind.executePendingBindings()
        }, 0)
    }
}