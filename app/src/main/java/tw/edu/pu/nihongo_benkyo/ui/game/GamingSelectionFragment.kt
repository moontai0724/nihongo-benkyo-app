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
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGamingSelectionBinding
import tw.edu.pu.nihongo_benkyo.model.database.Question

class GamingSelectionFragment : Fragment() {
    lateinit var dataBinding: FragmentGamingSelectionBinding
    lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentGamingSelectionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        return dataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        dataBinding.viewModel = viewModel
        val questions = arguments?.getParcelableArrayList<Question>("questions")
        val tags = arguments?.getLongArray("tags")!!
        val type = arguments?.getLong("type")!!
        if (questions == null) {
            viewModel.getQuestion(type, tags)
        } else {
            viewModel.questions.postValue(questions)
        }
        viewModel.questions.observe(viewLifecycleOwner, {
            viewModel.currentQuestion.postValue(if (it.isEmpty()) null else it[0])
            if (it.isNotEmpty())
                viewModel.initializeQuestion(type, tags)
        })

        viewModel.currentQuestion.observe(viewLifecycleOwner, {
            if (it == null){
                viewModel.noQuestionPop(requireContext()){
                    findNavController().popBackStack()
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.index < viewModel.questions.value!!.size)
            viewModel.deleteHistory()
    }
}