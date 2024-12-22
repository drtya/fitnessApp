package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DayFinishBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.FragmentManager
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