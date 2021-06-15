package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.nihongo_benkyo.databinding.FragmentHistoryDetailBinding
import tw.edu.pu.nihongo_benkyo.model.database.HistoryInfo

class HistoryDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentHistoryDetailBinding
    private lateinit var adapter: HistoryDetailAdapter
    private lateinit var viewModel: HistoryViewModel
    private lateinit var historyInfo: HistoryInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
        historyInfo = arguments?.getSerializable("historyInfo") as HistoryInfo
        adapter = HistoryDetailAdapter(historyInfo.type?.id!!, viewModel, historyInfo)
        viewModel.historyDetails.observe(viewLifecycleOwner, { adapter.setData(it) })

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        dataBinding.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllDetail(historyInfo.history?.id!!)
    }
}