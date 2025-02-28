package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.DayModel
import com.example.fitnessapp.adapters.DaysAdapter
import com.example.fitnessapp.adapters.ExercisesAdapter
import com.example.fitnessapp.databinding.DayFinishBinding
import com.example.fitnessapp.databinding.ExercisesListFragmentBinding
import com.example.fitnessapp.databinding.FragmentDaysBinding
import com.example.fitnessapp.databinding.WaitingFragmentBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.MainViewModel
import com.example.fitnessapp.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable

class DayFinishFragment : Fragment() {
    private lateinit var binding: DayFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DayFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val title = getString(R.string.done)
        ActionBarUtils().setActionBar(activity as AppCompatActivity,title)
        imageDone.setImageDrawable(GifDrawable(root.context.assets,"congrats.gif"))
        bDone.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(),activity as AppCompatActivity)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = DayFinishFragment()
    }

}