package com.example.task4.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task4.Adapters.HabitsPagerAdapter
import com.example.task4.Filter.FilterOrdering
import com.example.task4.Filter.HabitFilter
import com.example.task4.Filter.OrderingType
import com.example.task4.R
import com.example.task4.HabitsModel
import com.example.task4.Room.AppDatabase
import com.example.task4.ViewModels.HabitsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_first.*


class ContentMainFragment : Fragment() {
    lateinit var viewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HabitsViewModel(HabitsModel.init(AppDatabase.getDatabase(requireActivity()).HabitsDao())) as T
            }
        }).get(HabitsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager.adapter = HabitsPagerAdapter(this)
        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Positive"
                else -> "Negative"
            }
        }.attach()

        apply_filters.setOnClickListener{
            val filter = HabitFilter()
            filter.filterOrdering = FilterOrdering.valueOf(habit_ordering_filter.selectedItem.toString())
            filter.orderingType = OrderingType.valueOf(habit_ordering_type.selectedItem.toString())
            filter.habitName = habit_name_filter.text.toString()
            viewModel.setFilter(filter)
        }
        reset_filters.setOnClickListener{
            viewModel.resetFilter()
            habit_ordering_filter.setSelection(0)
            habit_ordering_type.setSelection(0)
            habit_name_filter.setText("")
        }

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_editor)
        }
    }
}