package tw.edu.pu.nihongo_benkyo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tw.edu.pu.nihongo_benkyo.R
import tw.edu.pu.nihongo_benkyo.databinding.FragmentGameSettingBinding

class GameSettingFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var dataBinding: FragmentGameSettingBinding
    private lateinit var adapter: GameSettingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        dataBinding = FragmentGameSettingBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
        gameViewModel.single.postValue(true)
        gameViewModel.single.observe(viewLifecycleOwner, {
            adapter = GameSettingAdapter(requireActivity(), gameViewModel, it)
            dataBinding.adapter = adapter
            if (it){
                dataBinding.recycler.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.pink_500
                    )
                )
                // get tag Data from database
                //adapter.setTagData()
            }
            else{
                dataBinding.recycler.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.pink_800
                    )
                )
                // get type Data from database
                //adapter.setTypeData()
            }


        })

    }

    override fun onResume() {
        super.onResume()
        val type = arguments?.getString("type")
        val tags = arguments?.getStringArrayList("tags")
        if (type != null && tags != null){
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putStringArrayList("tags", tags)
            findNavController().navigate(R.id.action_nav_game_to_gamingSelectionFragment, bundle)
        }
    }
}