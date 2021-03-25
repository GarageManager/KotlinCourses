package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_habit_info.*


class HabitEditor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_info)
        val itemPos = intent.getStringExtra("item_position")

        if (intent.getStringExtra("requestCode") == "2") {
            habit_name.setText(intent.getStringExtra("habit_name"))
            habit_description.setText(intent.getStringExtra("habit_description"))
            habit_periodicity.setText(intent.getStringExtra("habit_periodicity"))
            habit_priority.setSelection(getIndex(habit_priority, intent.getStringExtra("habit_priority")!!))
        }

        findViewById<Button>(R.id.save_habit).setOnClickListener {
            val intent = Intent(applicationContext, HabitEditor::class.java)
            intent.putExtra("habit_name", habit_name.text.toString())
            intent.putExtra("habit_description", habit_description.text.toString())
            intent.putExtra("habit_priority", habit_priority.selectedItem.toString())
            intent.putExtra("habit_periodicity", habit_periodicity.text.toString())
            intent.putExtra("habit_type", findViewById<RadioButton>(habit_types.checkedRadioButtonId).text.toString())
            intent.putExtra("item_position", itemPos)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
                return i
            }
        }
        return 0
    }
}