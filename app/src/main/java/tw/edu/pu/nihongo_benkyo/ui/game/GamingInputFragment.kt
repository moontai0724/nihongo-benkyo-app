package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGamingInputBinding

class GamingInputFragment : Fragment() {
    lateinit var dataBinding: FragmentGamingInputBinding
    lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentGamingInputBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        arguments?.getString("tags")?.let {
            arguments?.getLong("type")?.let { it1 ->
                viewModel.getQuestion(it1, it)
            }
        }
        viewModel.questions.observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                viewModel.currentQuestion.postValue(it[0])
        })
    }

}