package com.deezork.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deezork.app1.databinding.ActivityDetailsBinding
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    //Привязываем View из layout к переменным
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setFilmsDetails()

        val snackbar = Snackbar.make(binding.detailsLayout, "", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("В Избранное",{
            Toast.makeText(this,"Фильм добавлен в Избранное",Toast.LENGTH_SHORT).show()
        })
//        snackbar.setAction("Посмотреть",{
//            Toast.makeText(this,"Смотрим Фильм",Toast.LENGTH_SHORT).show()
//        })
        snackbar.show()
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        val film = intent.extras?.get("film") as Film
        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        binding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description
    }
}