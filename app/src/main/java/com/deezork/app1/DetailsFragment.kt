package com.deezork.app1

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.deezork.app1.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    //Привязываем View из layout к переменным
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var film: Film
    init {
        enterTransition = Slide(Gravity.TOP).apply { duration = 500 }
        returnTransition = Slide(Gravity.TOP).apply { duration = 500;mode = Slide.MODE_OUT }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        //Получаем наш фильм из переданного бандла
        film = arguments?.get("film") as Film
        setFilmsDetails()
        setOnFilmFavorites()
        setOnFabShare()
    }

    private fun setFilmsDetails() {
        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        binding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description
        //Устанавливаем вид отображения FAB
        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite
        )
    }

    private fun setOnFilmFavorites() {
        binding.detailsFabFavorites.setOnClickListener {
            if (!film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_24)
                film.isInFavorites = true

            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite)
                film.isInFavorites = false
            }
        }
    }

    private fun setOnFabShare() {
        binding.detailsFab.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

}