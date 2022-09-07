package com.eugene.androidmaterialdesign.ui.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Pair<Data, Boolean>)
}