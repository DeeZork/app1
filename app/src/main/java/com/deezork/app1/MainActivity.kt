package com.deezork.app1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.deezork.app1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L
    private lateinit var binding: ActivityMainBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    val filmsDataBase = listOf(
        Film("Барби", R.drawable.barbie, "Барби"),
        Film("Дюна", R.drawable.dune, "Дюна"),
        Film("Балбесы", R.drawable.goonies, "Балбесы"),
        Film("Индиана Джонс", R.drawable.indianajones, "Индиана Джонс"),
        Film("Паразиты", R.drawable.parasite, "Паразиты"),
        Film("Крик", R.drawable.scream, "Крик"),
        Film("Звездные войны", R.drawable.starwars, "Звездные войны"),
        Film("Трансформеры", R.drawable.transformers, "Трансформеры"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initTopAppBar()
        initNavigation()
        //Зупускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment(filmsDataBase))
            .addToBackStack(null)
            .commit()
    }

    private fun initTopAppBar() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment( fragment?: HomeFragment(filmsDataBase), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(filmsDataBase), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: WatchLaterFragment(filmsDataBase), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SelectionsFragment(filmsDataBase), tag)
                    true
                }
                else -> false
            }
        }
    }
    //Ищем фрагмент по тэгу, если он есть то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle
        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else
                Toast.makeText(this, "DoubleClick for EXIT", Toast.LENGTH_SHORT).show()
            backPressed = System.currentTimeMillis()
        } else
            super.onBackPressed()
    }
    companion object {
        const val TIME_INTERVAL = 2000
    }
}