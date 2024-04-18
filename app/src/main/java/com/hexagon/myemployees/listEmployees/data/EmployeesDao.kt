package com.hexagon.myemployees.listEmployees.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.hexagon.myemployees.core.domain.models.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeesDao {

    @Upsert
    suspend fun upsertEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun getAllEmployee(): Flow<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id LIMIT 1")
    fun getEmployeeById(id: Int): Flow<List<Employee>>

    @Query("DELETE FROM employee WHERE id = :id")
    fun deleteEmployeeById(id: Int)

    @Query("UPDATE employee SET isActive = NOT isActive WHERE id = :id")
    fun toggleIsActiveById(id: Int)
}