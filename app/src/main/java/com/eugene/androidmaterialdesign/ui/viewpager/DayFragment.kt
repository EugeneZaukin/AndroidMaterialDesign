package com.eugene.androidmaterialdesign.ui.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.databinding.FragmentDayBinding
import com.eugene.androidmaterialdesign.ui.main.MainViewModel
import com.eugene.androidmaterialdesign.ui.main.PictureOfTheDayData

private const val URL_KEY = "url"

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

    private var url: String? = null

    companion object {
        @JvmStatic
        fun newInstance(date: String) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_KEY, url)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL_KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }
}