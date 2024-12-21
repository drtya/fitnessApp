package com.example.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.DayModel
import com.example.fitnessapp.adapters.DaysAdapter
import com.example.fitnessapp.adapters.ExercisesModel
import com.example.fitnessapp.databinding.FragmentDaysBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.DialogManager
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.MainViewModel

class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()
    private lateinit var adapter: DaysAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentDay = 0
        ActionBarUtils().setActionBar(activity as AppCompatActivity, getString(R.string.days))
        initRcView()
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tArray = ArrayList<DayModel>()
        var doneDaysCounter = 0
        resources.getStringArray(R.array.days_exercises).forEach { it ->
            model.currentDay++
            val exCounter = it.split(",").size
            tArray.add(DayModel(it, 0, model.getExerciseCount() == exCounter))
        }
        tArray.forEach { it ->
            if (it.isDone) doneDaysCounter++
        }
        updateRestDaysUI(tArray.size, doneDaysCounter)

        return tArray
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reset) {
            DialogManager.showDialog(
                activity as AppCompatActivity,
                R.string.reset_days_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.pref?.edit()?.clear()?.apply()
                        adapter.submitList(fillDaysArray())
                    }

                }
            )

        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateRestDaysUI(allDays: Int, doneDays: Int) = with(binding) {
        val restDays = allDays - doneDays
        val title = getString(R.string.rest) + " $restDays " + getString(R.string.rest_days)
        tvRestDays.text = title
        pB.max = allDays
        pB.progress = doneDays
    }

    private fun fillExercisesList(day: DayModel) {
        val tempList = ArrayList<ExercisesModel>()
        day.exercises.split(',').forEach { it ->
            val exerciseList = resources.getStringArray(R.array.exercise)
            val exercise = exerciseList[it.toInt()].split('|')
            tempList.add(ExercisesModel(exercise[0], exercise[1], exercise[2], false))
        }
        model.mutableListExercise.value = tempList
    }

    private fun initRcView() = with(binding) {
        adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(day: DayModel) {
        if (!day.isDone) {
            fillExercisesList(day)
            model.currentDay = day.dayNumber
            FragmentManager.setFragment(
                ExercisesListFragment.newInstance(),
                activity as AppCompatActivity
            )
        } else {
            DialogManager.showDialog(
                activity as AppCompatActivity,
                R.string.reset_day_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.savePref(day.dayNumber.toString(), 0)
                        fillExercisesList(day)
                        model.currentDay = day.dayNumber
                        FragmentManager.setFragment(
                            ExercisesListFragment.newInstance(),
                            activity as AppCompatActivity
                        )
                    }
                }
            )
        }

    }
}