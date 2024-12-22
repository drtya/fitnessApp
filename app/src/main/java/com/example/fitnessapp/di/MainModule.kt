package com.example.fitnessapp.di

import android.app.Application
import androidx.room.Room
import com.example.fitnessapp.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides // предоставление доступа к бд
    @Singleton // для создания одной инстанции и ее использовния(иначе инстанции будут создаваться с каждым вызовом/ так я понял)
    fun provideMainDb(app:Application):MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "fitness.db"
        ).createFromAsset("db/fitness.db").build()
    }
}