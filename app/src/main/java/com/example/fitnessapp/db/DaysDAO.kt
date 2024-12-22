package com.example.fitnessapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaysDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // если элемент существует, изменит существующий объект
    suspend fun insertDay(dayModel: DayModel)

    @Query("SELECT * FROM day_model_table WHERE id = :dayId")
    suspend fun getDayById(dayId: Int): DayModel

    @Query("SELECT * FROM day_model_table WHERE difficulty = :difficulty")
    suspend fun getAllDaysByDifficulty(difficulty: String): Flow<List<DayModel>>
}