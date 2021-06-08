package tw.edu.pu.nihongo_benkyo.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tw.edu.pu.nihongo_benkyo.databinding.FragmentAllHistoryBinding

class AllHistoryFragment : Fragment() {

    private lateinit var binding: FragmentAllHistoryBinding
    private lateinit var adapter: AllHistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllHistoryBinding.inflate(inflater, container, false)
        adapter = AllHistoryAdapter()
        binding.adapter = adapter
        return binding.root
    }

}