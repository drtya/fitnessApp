package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.DayModel
import com.example.fitnessapp.adapters.DaysAdapter
import com.example.fitnessapp.adapters.ExercisesAdapter
import com.example.fitnessapp.databinding.ExercisesListFragmentBinding
import com.example.fitnessapp.databinding.FragmentDaysBinding
import com.example.fitnessapp.databinding.WaitingFragmentBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.MainViewModel
import com.example.fitnessapp.utils.TimeUtils

const val COUNT_DOWN_TIME = 11000L
class WaitingFragment : Fragment() {
    private lateinit var binding: WaitingFragmentBinding
    private lateinit var adapter: ExercisesAdapter
    private lateinit var timer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WaitingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = getString(R.string.waiting)
        ActionBarUtils().setActionBar(activity as AppCompatActivity,title)
        binding.prB.max = COUNT_DOWN_TIME.toInt()
        startTimer()
    }

    private fun startTimer() = with(binding){
        timer = object : CountDownTimer(COUNT_DOWN_TIME,10){
            override fun onTick(resTime: Long) {
                tvTimer.text = TimeUtils.getTime(resTime)
                prB.progress =resTime.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(ExercisesFragment.newInstance(),activity as AppCompatActivity)
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WaitingFragment()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }
}