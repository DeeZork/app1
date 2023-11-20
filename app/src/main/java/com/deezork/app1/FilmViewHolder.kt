package com.deezork.app1

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deezork.app1.databinding.FilmItemBinding

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Привязываем View из layout к переменным
    private val binding=FilmItemBinding.bind(itemView)

    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) = with(binding){
        title.text = film.title
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        description.text = film.description
    }

}