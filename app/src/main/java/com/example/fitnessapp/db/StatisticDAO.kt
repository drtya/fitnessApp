package com.example.fitnessapp.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface StatisticDAO {
    @Query("SELECT * FROM statistic_table")
    suspend fun getStatistic(): List<StatisticModel>

    @Query("SELECT * FROM statistic_table WHERE date = :date")
    suspend fun getStatisticByDate(date:String): StatisticModel

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertDayStatistic(statisticModel: StatisticModel)
}