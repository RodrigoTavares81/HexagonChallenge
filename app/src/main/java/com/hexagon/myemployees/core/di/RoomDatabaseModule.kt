package com.hexagon.myemployees.core.di

import android.content.Context
import androidx.room.Room
import com.hexagon.myemployees.core.data.AppDatabase
import com.hexagon.myemployees.listEmployees.data.EmployeesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = "myemployees.db"
        ).build()

    @Provides
    @Singleton
    fun providesEmployeesDao(appDatabase: AppDatabase): EmployeesDao = appDatabase.employeesDao()
}