package com.example.task4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_negative_habits.*
import java.util.ArrayList

class NegativeHabits : Fragment(), IHabitsList {
    override val recyclerViewItems = ArrayList<HabitInfo>()
    override lateinit var recyclerAdapter: CustomRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = CustomRecyclerAdapter(recyclerViewItems, negative_habits, this)
        negative_habits.adapter = recyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_negative_habits, container, false)
    }
}