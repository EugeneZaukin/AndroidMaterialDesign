package com.eugene.androidmaterialdesign.ui.settings

import android.os.Bundle
import android.view.*
import androidx.core.view.get
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.eugene.androidmaterialdesign.*
import com.eugene.androidmaterialdesign.databinding.FragmentSettingsBinding
import com.google.android.material.chip.Chip

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SettingsViewModel> {
        requireContext().appComponent.viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        //Применяем тему
        requireActivity().setTheme(viewModel.getTheme())
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChipGroup()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.themeByClick.collect {
                requireActivity().setTheme(it)
                requireActivity().recreate()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.chipCheckedState.collect {
                (binding.chipGroup[it] as Chip).isChecked = true
            }
        }

    }

    private fun initChipGroup() {
        with(binding) {
            chipMarsTheme.setOnClickListener { viewModel.onClickSaveTheme(MARS_THEME) }
            chipSpaceTheme.setOnClickListener { viewModel.onClickSaveTheme(SPACE_THEME) }
        }
    }
}