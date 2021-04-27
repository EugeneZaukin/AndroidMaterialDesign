package com.eugene.androidmaterialdesign.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.eugene.androidmaterialdesign.R
import com.eugene.androidmaterialdesign.ui.main.MainViewModel
import com.eugene.androidmaterialdesign.ui.main.PictureOfTheDayData
import java.util.*

class TodayFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val currentDate = Date();
        val date = "${currentDate.year}-${currentDate.month}-${currentDate.day}"
        viewModel.getData(date).observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отображение ошибки
                } else {
                    val imageView = view?.findViewById<ImageView>(R.id.image_view_today);
                    imageView?.load(url) {
                        lifecycle(this@TodayFragment)
                        error(R.drawable.errorimage)
                        placeholder(R.drawable.formatimage)
                    }
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
}