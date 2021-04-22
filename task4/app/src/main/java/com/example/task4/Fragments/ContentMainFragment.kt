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
import com.example.task4.R
import com.example.task4.ViewModels.HabitsList.FilterOrdering
import com.example.task4.ViewModels.HabitsList.HabitsModel
import com.example.task4.ViewModels.HabitsList.HabitsViewModel
import com.example.task4.ViewModels.HabitsList.OrderingType
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
                return HabitsViewModel(HabitsModel()) as T
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
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_editor)
        }
        apply_filters.setOnClickListener{
            val profile = viewModel.profile.value!!
            profile.filter.filterOrdering = FilterOrdering.valueOf(habit_ordering_filter.selectedItem.toString())
            profile.filter.orderingType = OrderingType.valueOf(habit_ordering_type.selectedItem.toString())
            profile.filter.habitName = habit_name_filter.text.toString()
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
        reset_filters.setOnClickListener{
            val profile = viewModel.profile.value!!
            habit_ordering_filter.setSelection(0)
            habit_ordering_type.setSelection(0)
            habit_name_filter.setText("")
            profile.filter.filterOrdering = FilterOrdering.None
            profile.filter.orderingType = OrderingType.None
            profile.filter.habitName = ""
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
    }
}