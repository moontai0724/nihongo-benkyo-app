package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGamingInputBinding

class GamingInputFragment : Fragment() {
    lateinit var dataBinding: FragmentGamingInputBinding
    lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentGamingInputBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        return dataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        dataBinding.viewModel = viewModel
        viewModel.questions.postValue(ArrayList())
        arguments?.getLongArray("tags")?.let {
            arguments?.getLong("type")?.let { it1 ->
                viewModel.getQuestion(it1, it)
            }
        }
        viewModel.questions.observe(viewLifecycleOwner, {
            viewModel.currentQuestion.postValue(if (it.isEmpty()) null else it[0])
            if (it.isEmpty()){
                viewModel.noQuestionPop(requireContext()){
                    findNavController().popBackStack()
                }
            }
        })
    }

}