package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.databinding.FragmentAllHistoryBinding

class AllHistoryFragment : Fragment() {

    private lateinit var binding: FragmentAllHistoryBinding
    private lateinit var adapter: AllHistoryAdapter
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllHistoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllHistory()
    }
}