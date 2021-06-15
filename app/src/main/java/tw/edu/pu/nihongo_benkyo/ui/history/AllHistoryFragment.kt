package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import tw.edu.pu.nihongo_benkyo.databinding.FragmentAllHistoryBinding
import tw.edu.pu.nihongo_benkyo.model.database.Question

class AllHistoryFragment : Fragment() {

    private lateinit var binding: FragmentAllHistoryBinding
    private lateinit var adapter: AllHistoryAdapter
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllHistoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = activity
        adapter = AllHistoryAdapter(viewModel)
        binding.adapter = adapter
        viewModel.allHistory.observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                adapter.setHistory(it)
        })
        viewModel.historyQuestions.observe(viewLifecycleOwner, {
            val bundle = Bundle()
            bundle.putLong("type", viewModel.historyInfo.value?.type?.id!!)
            bundle.putParcelableArrayList("questions", it as ArrayList<Question>)
            Navigation.findNavController(view).navigate(viewModel.tagetFragment.value!!, bundle)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllHistory()
    }
}