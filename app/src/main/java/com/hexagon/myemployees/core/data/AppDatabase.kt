package com.hexagon.myemployees.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hexagon.myemployees.listEmployees.data.EmployeesDao
import com.hexagon.myemployees.core.domain.models.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeesDao
}