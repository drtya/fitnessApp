package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ExercisesModel
import com.example.fitnessapp.databinding.ExerciseBinding
import com.example.fitnessapp.utils.ActionBarUtils
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.MainViewModel
import com.example.fitnessapp.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable

class ExercisesFragment : Fragment() {
    private lateinit var binding: ExerciseBinding
    private var exCounter = 0
    private var  exList : ArrayList<ExercisesModel>? = null
    private val model: MainViewModel by activityViewModels()
    private var timer: CountDownTimer? =null
    private var currentDay = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentDay = model.currentDay
        exCounter= model.getExerciseCount()
        model.mutableListExercise.observe(viewLifecycleOwner) {
            exList= it
            nextExercise()
        }
        binding.bNext.setOnClickListener {
            nextExercise()
        }
    }
    private fun nextExercise(){
        if (exCounter < exList?.size!!){
            val ex = exList?.get(exCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
            showNextExercise()
        }
        else{
            exCounter++
            FragmentManager.setFragment(DayFinishFragment.newInstance(),activity as AppCompatActivity)
//            Toast.makeText(activity,"Done",Toast.LENGTH_LONG).show()
        }
    }

    private fun showNextExercise()=with(binding){
        if (exCounter < exList?.size!!){
            val ex = exList?.get(exCounter) ?: return
            imNext.setImageDrawable(GifDrawable(root.context.assets,ex.image))
            val name = ex.name + ":${getTimeType(ex.time)}"
            tvNextName.text =name
        }
        else{
            imNext.setImageDrawable(GifDrawable(root.context.assets,"congrats.gif"))
            tvNextName.text = getString(R.string.done)
        }
    }

    private fun getTimeType(timeOrCount: String) :String {
        if (timeOrCount.startsWith("x")){
            return timeOrCount
        }
        else{
            return TimeUtils.getTime(timeOrCount.toLong()*1000)
        }
    }



    private fun showExercise(exercise:ExercisesModel) = with(binding){
        imMain.setImageDrawable( GifDrawable(root.context.assets,exercise.image))
        tvName.text = exercise.name
        val title = "$exCounter / ${exList?.size}"
        ActionBarUtils().setActionBar(activity as AppCompatActivity,title)
    }
    private fun setExerciseType(exercise:ExercisesModel) = with(binding){
        if (exercise.time.startsWith("x")){
            binding.tvTime.text = exercise.time
        }
        else{
            startTimer(exercise)
        }
    }

    private fun startTimer(exercise:ExercisesModel) = with(binding){
        progressBar.max = exercise.time.toInt()*1000
        timer?.cancel()
        timer = object : CountDownTimer(exercise.time.toLong()*1000,10){
            override fun onTick(resTime: Long) {
                tvTime.text = TimeUtils.getTime(resTime)
                progressBar.progress = resTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }
        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        model.savePref(currentDay.toString(),exCounter-1)
        timer?.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExercisesFragment()
    }
}