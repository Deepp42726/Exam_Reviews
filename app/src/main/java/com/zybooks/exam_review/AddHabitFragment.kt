package com.zybooks.exam_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zybooks.exam_review.R
import com.zybooks.exam_review.Habit
import java.io.File

class AddHabitFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_add_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameInput = view.findViewById<EditText>(R.id.editHabitName)
        val goalInput = view.findViewById<EditText>(R.id.editHabitGoal)
        val timePicker = view.findViewById<TimePicker>(R.id.habitTimePicker)
        val saveButton = view.findViewById<Button>(R.id.btnSaveHabit)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val goal = goalInput.text.toString().trim()
            val time = String.format("%02d:%02d", timePicker.hour, timePicker.minute)

            val newHabit = Habit(name, time, goal)

            val file = File(requireContext().filesDir, "habits.txt")
            val gson = Gson()
            val existingHabits: MutableList<Habit> = if (file.exists()) {
                val json = file.readText()
                gson.fromJson(json, object : TypeToken<MutableList<Habit>>() {}.type)
            } else {
                mutableListOf()
            }

            existingHabits.add(newHabit)
            file.writeText(gson.toJson(existingHabits))

            Toast.makeText(requireContext(), "Habit saved", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
}