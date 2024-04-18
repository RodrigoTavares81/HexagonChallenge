package com.hexagon.myemployees.editEmployee.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hexagon.myemployees.core.domain.models.Employee
import com.hexagon.myemployees.listEmployees.data.EmployeesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EditEmployeeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val employeesDao: EmployeesDao
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<EditEmployeeState> =
        MutableStateFlow(EditEmployeeState())

    val stateFlow: StateFlow<EditEmployeeState> = _stateFlow.asStateFlow()

    fun loadEmployee(employeeId: Int) {
        viewModelScope.launch {
            employeesDao.getEmployeeById(employeeId).collect { employeeList ->
                val employee = employeeList.firstOrNull() ?: return@collect
                _stateFlow.update {
                    it.copy(employeeData = employee)
                }
            }
        }
    }

    fun saveEmployee(employee: Employee) {
        viewModelScope.launch {
            employeesDao.upsertEmployee(employee)
        }
    }

    fun updateName(newName: String) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(name = newName)) }
    }

    fun updateBirthDate(newBirthDate: String) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(birthDate = newBirthDate)) }
    }

    fun updateCity(newCity: String) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(city = newCity)) }
    }

    fun updateCPF(newCPF: String) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(cpf = newCPF)) }
    }

    fun updateIsActive(isActive: Boolean) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(isActive = isActive)) }
    }

    fun updatePhotographURI(newPhotographURI: String) {
        _stateFlow.update { it.copy(employeeData = it.employeeData.copy(photographURI = newPhotographURI)) }
    }
}