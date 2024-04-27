package com.hexagon.myemployees.listEmployees.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.hexagon.myemployees.core.presentation.navigation.Arguments
import com.hexagon.myemployees.core.presentation.navigation.Routes

@Composable
fun EmployeesRoute(
    navController: NavController,
    coordinator: EmployeesCoordinator = rememberEmployeesCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(EmployeesState())

    val actions = rememberEmployeesActions(coordinator, navController)

    EmployeesScreen(uiState, actions)
}


@Composable
fun rememberEmployeesActions(
    coordinator: EmployeesCoordinator,
    navController: NavController
): EmployeesActions =
    remember(coordinator) {
        EmployeesActions(
            addNewEmployee = {
                val route = Routes.EditEmployee.linkWithEmployeeId()
                navController.navigate(route)
            },
            editEmployee = { employeeId ->
                val route = Routes.EditEmployee.linkWithEmployeeId(employeeId)
                navController.navigate(route)
            },
            deleteEmployee = { employeeId ->
                employeeId?.let {
                    coordinator.viewModel.deleteEmployee(it)
                }
            },
            toggleIsActive = { employeeId ->
                employeeId?.let {
                    coordinator.viewModel.toggleEmployeeIsActive(employeeId)
                }
            },
        )
    }