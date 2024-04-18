package com.hexagon.myemployees.listEmployees.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hexagon.myemployees.core.domain.models.Employee
import com.hexagon.myemployees.listEmployees.data.EmployeesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val employeesDao: EmployeesDao,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<EmployeesState> = MutableStateFlow(EmployeesState())

    val stateFlow: StateFlow<EmployeesState> = _stateFlow.asStateFlow()

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                employeesDao.getAllEmployee().collect { allEmployees ->
                    _stateFlow.update {
                        it.copy(employees = allEmployees)
                    }
                }
            }
        }
    }

    fun deleteEmployee(id: Int) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                employeesDao.deleteEmployeeById(id)
            }
        }
    }

    fun toggleEmployeeIsActive(id: Int) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                employeesDao.toggleIsActiveById(id)
            }
        }
    }
}