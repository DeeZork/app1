package com.deezork.app1

import androidx.recyclerview.widget.DiffUtil

class FilmDiff(val oldList: ArrayList <Film> , val newList: ArrayList <Film>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getNewListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }
}