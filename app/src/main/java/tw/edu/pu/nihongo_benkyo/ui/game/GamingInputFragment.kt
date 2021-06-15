package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGamingInputBinding
import tw.edu.pu.nihongo_benkyo.model.database.Question

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        val questions = arguments?.getParcelableArrayList<Question>("questions")
        if (questions == null) {
            viewModel.questions.postValue(ArrayList())
            arguments?.getLongArray("tags")?.let {
                arguments?.getLong("type")?.let { it1 ->
                    viewModel.getQuestion(it1, it)
                }
            }
        } else {
            viewModel.questions.postValue(questions)
        }
        viewModel.questions.observe(viewLifecycleOwner, {
            viewModel.currentQuestion.postValue(if (it.isEmpty()) null else it[0])
        })
    }
}