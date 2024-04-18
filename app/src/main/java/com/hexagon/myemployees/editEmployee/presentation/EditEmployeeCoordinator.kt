package com.hexagon.myemployees.editEmployee.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.hexagon.myemployees.core.domain.models.Employee

class EditEmployeesCoordinator(
    employeeId: Int?,
    val viewModel: EditEmployeeViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    init {
        employeeId?.let {
            viewModel.loadEmployee(it)
        }
    }
}

@Composable
fun rememberEditEmployeesCoordinator(
    employeeId: Int?,
    viewModel: EditEmployeeViewModel = hiltViewModel()
): EditEmployeesCoordinator = remember(viewModel) {
    EditEmployeesCoordinator(
        employeeId = employeeId,
        viewModel = viewModel
    )
}