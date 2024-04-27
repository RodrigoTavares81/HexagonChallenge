package com.hexagon.myemployees.listEmployees.presentation

import com.hexagon.myemployees.core.domain.models.Employee

data class EmployeesState(
    val employees: List<Employee> = emptyList()
)

data class EmployeesActions(
    val addNewEmployee: () -> Unit,
    val editEmployee: (employeeId: Int?) -> Unit,
    val deleteEmployee: (employeeId: Int?) -> Unit,
    val toggleIsActive: (employeeId: Int?) -> Unit,
)