package com.example.fitnessapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExercisesModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var name:String,
    var subTitle:String,
    var time:String,
    var isDone:Boolean,
    var image:String,
    var kcal:Int,
)
