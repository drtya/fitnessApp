package com.example.fitnessapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DayModel::class,
        ExercisesModel::class,
        WeightModel::class,
        StatisticModel::class
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val daysDAO: DaysDAO
    abstract val exerciseDAO: ExerciseDAO
    abstract val weightDAO: WeightDAO
    abstract val statisticDAO: StatisticDAO

}
