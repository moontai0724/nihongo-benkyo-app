package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGamingSelectionBinding

class GamingSelectionFragment : Fragment() {

    lateinit var dataBinding: FragmentGamingSelectionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentGamingSelectionBinding.inflate(inflater, container, false)
        return dataBinding.root
    }
}