package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ExercisesAdapter
import com.example.fitnessapp.databinding.ExercisesListFragmentBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.MainViewModel

class ExercisesListFragment : Fragment() {
    private lateinit var binding: ExercisesListFragmentBinding
    private lateinit var adapter: ExercisesAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExercisesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title= getString(R.string.exercises)
        ActionBarUtils().setActionBar(activity as AppCompatActivity,title)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner,{
            it->
            for (i in 0 until model.getExerciseCount() ){
                it[i]= it[i].copy(isDone = true)
            }
            adapter.submitList(it)
        })
    }

    private fun init() = with(binding){
        adapter = ExercisesAdapter()
        buttonStart.setOnClickListener{
            FragmentManager.setFragment(WaitingFragment.newInstance(),activity as AppCompatActivity)
        }
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter =adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() = ExercisesListFragment()
    }
}