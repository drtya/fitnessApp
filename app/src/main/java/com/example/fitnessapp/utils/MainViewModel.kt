package com.example.fitnessapp.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.db.ExercisesModel

class MainViewModel:ViewModel() {
    val mutableListExercise =MutableLiveData<ArrayList<ExercisesModel>>()
    var pref: SharedPreferences ?= null
    var currentDay = 0

    fun savePref(key:String,value:Int){
        pref?.edit()?.putInt(key,value)?.apply()
    }

    fun getExerciseCount():Int{
        return pref?.getInt(currentDay.toString(),0) ?: 0 //если слева null, ответом будет значение справа
    }
}