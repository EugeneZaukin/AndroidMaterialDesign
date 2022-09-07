package com.eugene.androidmaterialdesign.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.transition.*
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.appComponent
import com.eugene.androidmaterialdesign.databinding.MainFragmentBinding
import com.eugene.androidmaterialdesign.domain.Date
import com.eugene.androidmaterialdesign.ui.settings.SettingsFragment
import com.eugene.androidmaterialdesign.ui.notes.RecyclerActivity
import com.eugene.androidmaterialdesign.ui.main.viewpager.ViewPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        requireContext().appComponent.viewModelFactory
    }

    private var animationPosition = 2
    private var date = Date()

    private lateinit var pageListener: ViewPager2.OnPageChangeCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomAppBar()
        setBottomSheetBehavior()
        initSearchClick()
        initViewPager()
        binding.tvDate.text = date.today

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.titleText.collect { binding.bottomSheetDescription.bsTitle.text = it }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.descriptionText.collect { binding.bottomSheetDescription.bsContent.text = it }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                if (it.isNotEmpty()) Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setBottomAppBar() {
        val appActivity = activity as AppCompatActivity
        appActivity.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun setBottomSheetBehavior() {
        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> =
            BottomSheetBehavior.from(binding.bottomSheetDescription.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initSearchClick() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
    }

    private fun initViewPager() {
        var dateForRequest = date.today
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity(), date)
//        binding.viewPager.currentItem = 2

        pageListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        dateForRequest = date.theDayBeforeYesterday
                        animationPosition = 0
                    }
                    1 -> {
                        dateForRequest = date.yesterday
                        animationPosition = 1
                    }
                    2 -> {
                        dateForRequest = date.today
                        animationPosition = 2
                    }
                }

                val changeBounds = ChangeBounds()
                changeBounds.setPathMotion(ArcMotion())
                changeBounds.duration = 500
                TransitionManager.beginDelayedTransition(animation, changeBounds)
                val params = binding.tvDate.layoutParams as FrameLayout.LayoutParams
                params.gravity = when(animationPosition) {
                    0 -> Gravity.START
                    1 -> Gravity.CENTER
                    2 -> Gravity.END
                    else -> Gravity.END
                }
                binding.tvDate.layoutParams = params
                binding.tvDate.text = dateForRequest

                viewModel.sendServerRequest(dateForRequest)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(pageListener)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings ->
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SettingsFragment.newInstance())
                    ?.addToBackStack(null)
                    ?.commit()
            R.id.notes -> activity?.let { startActivity(Intent(it, RecyclerActivity::class.java)) }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(pageListener)
        _binding = null
    }
}