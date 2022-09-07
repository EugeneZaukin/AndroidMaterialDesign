package com.eugene.androidmaterialdesign.ui.main.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.appComponent
import com.eugene.androidmaterialdesign.databinding.FragmentDayBinding

private const val DATE = "date"

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DayFragmentViewModel> {
        requireContext().appComponent.viewModelFactory
    }

    private var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString(DATE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.urlString.collect(::loadImage)
        }

        viewModel.getPicture(date!!)
    }

    private fun loadImage(url: String) {
        binding.imageViewDay.load(url) {
            lifecycle(this@DayFragment)
            error(R.drawable.errorimage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(date: String) =
            DayFragment().apply {
                arguments = bundleOf(DATE to date)
            }
    }
}