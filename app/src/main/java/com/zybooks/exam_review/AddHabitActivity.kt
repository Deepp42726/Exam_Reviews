package com.zybooks.exam_review

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class AddHabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        val nameInput = findViewById<EditText>(R.id.editHabitName)
        val goalInput = findViewById<EditText>(R.id.editHabitGoal)
        val timePicker = findViewById<TimePicker>(R.id.habitTimePicker)
        val saveButton = findViewById<Button>(R.id.btnSaveHabit)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val goal = goalInput.text.toString().trim()
            val time = String.format("%02d:%02d", timePicker.hour, timePicker.minute)

            val newHabit = Habit(name, time, goal)

            val file = File(filesDir, "habits.txt")
            val gson = Gson()
            val existingHabits: MutableList<Habit> = if (file.exists()) {
                val json = file.readText()
                gson.fromJson(json, object : TypeToken<MutableList<Habit>>() {}.type)
            } else {
                mutableListOf()
            }

            existingHabits.add(newHabit)
            file.writeText(gson.toJson(existingHabits))

            Toast.makeText(this, "Habit saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}