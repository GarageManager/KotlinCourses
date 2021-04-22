package com.example.task4.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task4.HabitType
import com.example.task4.Fragments.HabitsListFragment

class HabitsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment  = when (position){
        0 -> {
            HabitsListFragment.newInstance(HabitType.Positive)
        }
        else -> {
            HabitsListFragment.newInstance(HabitType.Negative)
        }
    }
}