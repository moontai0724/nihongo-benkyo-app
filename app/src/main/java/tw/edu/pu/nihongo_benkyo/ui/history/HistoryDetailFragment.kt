package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.FragmentHistoryDetailBinding

class HistoryDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentHistoryDetailBinding
    private lateinit var adapter: HistoryDetailAdapter
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
        val historyId = arguments?.getLong("historyId")!!
        adapter = HistoryDetailAdapter(1, viewModel)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        dataBinding.adapter = adapter
    }
}