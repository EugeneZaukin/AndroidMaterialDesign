package com.eugene.androidmaterialdesign.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.eugene.androidmaterialdesign.MainActivity
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.ui.SettingsFragment
import com.eugene.androidmaterialdesign.ui.viewpager.Date
import com.eugene.androidmaterialdesign.ui.viewpager.ViewPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bsTittle: TextView
    private lateinit var bsContent: TextView
    private lateinit var textDate: TextView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Добавляем адаптер
        view_pager.adapter = ViewPagerAdapter(childFragmentManager)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        bsTittle = view.findViewById(R.id.bottom_sheet_description_header)
        bsContent = view.findViewById(R.id.bottom_sheet_description)
        textDate = view.findViewById(R.id.text_view_date)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val currentData = Date()
        var date = "${currentData.year}-${currentData.month}-${currentData.day}"

        //Определяем первональную позицию
        view_pager.currentItem = 1
        //Добавлчям листенер на свайп ViewPger чтобы обновлять текст в BottomSheetBehavior
        view_pager.addOnPageChangeListener(object  : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> date = "${currentData.year}-${currentData.month}-${currentData.day-1}"
                    1 -> date = "${currentData.year}-${currentData.month}-${currentData.day}"
                }
                viewModel.getData(date).observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
                textDate.text = date
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

        viewModel.getData(date).observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
        textDate.text = date
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отображение ошибки
                } else {
                    bsTittle.text = serverResponseData.title
                    bsContent.text = serverResponseData.explanation
                }
            }
            is PictureOfTheDayData.Loading -> {
                //Загрузка
            }
            is PictureOfTheDayData.Error -> {
                //Ошибка
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
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
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
    }
}