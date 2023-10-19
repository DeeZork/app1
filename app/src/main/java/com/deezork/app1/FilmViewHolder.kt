package com.deezork.app1

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.deezork.app1.databinding.FilmItemBinding

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Привязываем View из layout к переменным
    private val binding=FilmItemBinding.bind(itemView)

    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) = with(binding){
        title.text = film.title
        poster.setImageResource(film.poster)
        description.text = film.description
    }

}