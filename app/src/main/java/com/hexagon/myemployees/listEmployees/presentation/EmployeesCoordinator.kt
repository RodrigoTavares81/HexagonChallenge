package com.hexagon.myemployees.listEmployees.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeesCoordinator(
    val viewModel: EmployeesViewModel
) {

    val screenStateFlow = viewModel.stateFlow

}

@Composable
fun rememberEmployeesCoordinator(
    viewModel: EmployeesViewModel = hiltViewModel()
): EmployeesCoordinator = remember(viewModel) {
    EmployeesCoordinator(viewModel = viewModel)
}