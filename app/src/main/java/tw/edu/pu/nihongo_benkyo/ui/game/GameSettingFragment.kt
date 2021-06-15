package tw.edu.pu.nihongo_benkyo.ui.game

import android.app.ProgressDialog
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
import tw.edu.pu.nihongo_benkyo.model.database.Question

class GameSettingFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var dataBinding: FragmentGameSettingBinding
    private lateinit var adapter: GameSettingAdapter
    private lateinit var progressDialog: ProgressDialog
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
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("載入資料中...")
        progressDialog.show()
        dataBinding.lifecycleOwner = activity
        dataBinding.viewModel = gameViewModel
        gameViewModel.single.postValue(true)
        gameViewModel.single.observe(viewLifecycleOwner, {
            adapter = GameSettingAdapter(requireActivity(), gameViewModel, it)
            dataBinding.adapter = adapter
            if (it) {
                dataBinding.recycler.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.pink_800
                    )
                )
                gameViewModel.getType()
            } else {
                dataBinding.recycler.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.pink_500
                    )
                )
                gameViewModel.getTags()
            }
        })

        gameViewModel.types.observe(viewLifecycleOwner, {
            adapter.setTypeData(it)
        })

        gameViewModel.tags.observe(viewLifecycleOwner, {
            adapter.setTagData(it)
        })

        if (arguments?.getParcelableArrayList<Question>("questions") == null) {
            progressDialog.dismiss()
            return
        }
    }

    override fun onResume() {
        super.onResume()
        val type = arguments?.getLong("type")
        if (type != null) {
            progressDialog.dismiss()
            when (type.toInt()) {
                1 -> findNavController().navigate(
                    R.id.action_nav_game_to_gamingSelectionFragment,
                    arguments
                )
                else -> findNavController().navigate(
                    R.id.action_nav_game_to_gamingInputFragment,
                    arguments
                )
            }
        } else {
            gameViewModel.selectTag = ArrayList()
            gameViewModel.type = 0
        }
    }
}