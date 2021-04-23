package com.eugene.androidmaterialdesign.ui.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import coil.api.load
import com.eugene.androidmaterialdesign.MainActivity
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.ui.SettingsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_activity.view.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bsTittle: TextView
    private lateinit var bsContent: TextView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        bsTittle = view.findViewById(R.id.bottom_sheet_description_header)
        bsContent = view.findViewById(R.id.bottom_sheet_description)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отображение ошибки
                } else {
                    val imageView = view?.findViewById<ImageView>(R.id.image_view);
                    imageView?.load(url) {
                        lifecycle(this@MainFragment)
                        error(R.drawable.errorimage)
                        placeholder(R.drawable.formatimage)
                    }
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