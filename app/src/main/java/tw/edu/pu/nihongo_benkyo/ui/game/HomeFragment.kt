package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.content.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_gameChoseFragment)
        }
        _binding!!.history.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}