package com.hexagon.myemployees.editEmployee.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun EditEmployeeRoute(
    navController: NavHostController,
    employeeId: Int?,
    coordinator: EditEmployeesCoordinator = rememberEditEmployeesCoordinator(employeeId),
) {
    val uiState by coordinator.screenStateFlow.collectAsState(EditEmployeeState())

    val actions = rememberEditEmployeeActions(coordinator, navController)

    EditEmployeeScreen(uiState, actions)
}


@Composable
fun rememberEditEmployeeActions(
    coordinator: EditEmployeesCoordinator,
    navController: NavHostController
): EditEmployeeActions = remember(coordinator) {
    EditEmployeeActions(
        onSave = { coordinator.viewModel.saveEmployee(it); navController.navigateUp() },
        onNameChanged = coordinator.viewModel::updateName,
        onBirthDateChanged = coordinator.viewModel::updateBirthDate,
        onCityChanged = coordinator.viewModel::updateCity,
        onCPFChanged = coordinator.viewModel::updateCPF,
        onIsActiveChanged = coordinator.viewModel::updateIsActive,
        onPhotographURIChanged = coordinator.viewModel::updatePhotographURI
    )
}