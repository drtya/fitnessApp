package com.example.fitnessapp.utils

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class ActionBarUtils {
    private var actionBar: ActionBar?= null

    fun setActionBar (activity: AppCompatActivity, title:String){
        actionBar= activity.supportActionBar
        actionBar?.title= title
    }
}