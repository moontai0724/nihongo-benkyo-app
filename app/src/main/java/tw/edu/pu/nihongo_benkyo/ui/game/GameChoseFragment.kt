package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGameChoseBinding
import java.util.*

class GameChoseFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var dataBinding: FragmentGameChoseBinding
    private lateinit var adapter: GameChoseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val typeArr =  ArrayList(listOf(*resources.getStringArray(R.array.question_type)))
        val contentArr =  ArrayList(listOf(*resources.getStringArray(R.array.question_content)))
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        dataBinding = FragmentGameChoseBinding.inflate(inflater, container, false)
        adapter = GameChoseAdapter()
        dataBinding.adapter = adapter
        gameViewModel.setData(typeArr)
        dataBinding.type.setOnClickListener {
            gameViewModel.setData(typeArr)
            dataBinding.recycler.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pink_800))
        }
        dataBinding.testContent.setOnClickListener {
            gameViewModel.setData(contentArr)
            dataBinding.recycler.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pink_500))
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