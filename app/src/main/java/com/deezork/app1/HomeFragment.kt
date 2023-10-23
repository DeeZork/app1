package com.deezork.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.deezork.app1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    val filmsDataBase = listOf(
        Film("Барби", R.drawable.barbie, "Барби"),
        Film("Дюна", R.drawable.goonies, "Дюна"),
        Film("Балбесы", R.drawable.dune, "Балбесы"),
        Film("Индиана Джонс", R.drawable.indianajones, "Индиана Джонс"),
        Film("Паразиты", R.drawable.parasite, "Паразиты"),
        Film("Крик", R.drawable.scream, "Крик"),
        Film("Звездные войны", R.drawable.starwars, "Звездные войны"),
        Film("Трансформеры", R.drawable.transformers, "Трансформеры"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //находим наш RV
        binding.mainRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }
    fun updateRV(newList: List<Film>) {
        val oldList = filmsAdapter.items
        val filmDiff = FilmDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(filmDiff)
        filmsAdapter.items = newList as MutableList<Film>
        diffResult.dispatchUpdatesTo(filmsAdapter)
    }
}