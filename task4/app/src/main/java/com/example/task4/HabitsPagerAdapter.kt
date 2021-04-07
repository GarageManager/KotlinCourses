package com.example.task4

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HabitsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment  = when (position){
        0 -> {
            PositiveHabits()
        }
        else -> {
            NegativeHabits()
        }
    }
}