package com.example.fitnessapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDAO {
    @Query("SELECT * FROM exercise_table")
    suspend fun getAllExercises()

}