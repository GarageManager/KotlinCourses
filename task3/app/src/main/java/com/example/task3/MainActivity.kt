package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val recyclerViewItems = ArrayList<HabitInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.add_habit).setOnClickListener {
            val intent = Intent(applicationContext, HabitEditor::class.java)
            intent.putExtra("requestCode", "1")
            intent.putExtra("item_position","${recyclerViewItems.size - 1}")
            startActivityForResult(intent, 1)
        }

        habits_list.layoutManager = LinearLayoutManager(this)
        habits_list.adapter = CustomRecyclerAdapter(recyclerViewItems, habits_list, this)
    }

    fun handleRvClick(item: HabitInfo, pos: Int?)
    {
        val intent = Intent(applicationContext, HabitEditor::class.java)
        intent.putExtra("habit_name", item.Name)
        intent.putExtra("habit_description", item.Description)
        intent.putExtra("habit_priority", item.Priority)
        intent.putExtra("habit_periodicity", item.Periodicity)
        intent.putExtra("habit_type", item.Type)
        intent.putExtra("requestCode", "2")
        intent.putExtra("item_position", "$pos")
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val habit = HabitInfo(data?.getStringExtra("habit_name"),
                data?.getStringExtra("habit_description"),
                data?.getStringExtra("habit_priority"),
                data?.getStringExtra("habit_periodicity"),
                data?.getStringExtra("habit_type"))
            when (requestCode) {
                1 -> {
                    recyclerViewItems.add(habit)
                    habits_list.adapter!!.notifyItemInserted(recyclerViewItems.size - 1)
                }
                2 -> {
                    val pos = data?.getStringExtra("item_position")!!.toInt()
                    recyclerViewItems[pos] = habit
                    habits_list.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }

}