package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGameSettingBinding
import java.util.*

class GameSettingFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var dataBinding: FragmentGameSettingBinding
    private lateinit var adapter: GameSettingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        dataBinding = FragmentGameSettingBinding.inflate(inflater, container, false)
        adapter = GameSettingAdapter()
        dataBinding.adapter = adapter
        gameViewModel.getType()
        dataBinding.type.setOnClickListener {
            gameViewModel.getType()
            dataBinding.recycler.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pink_800
                )
            )
        }
        dataBinding.testContent.setOnClickListener {
            gameViewModel.getTheme()
            dataBinding.recycler.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pink_500
                )
            )
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        gameViewModel.arr.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }

}