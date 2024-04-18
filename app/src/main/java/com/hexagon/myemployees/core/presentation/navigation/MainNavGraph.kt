package com.hexagon.myemployees.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hexagon.myemployees.editEmployee.presentation.EditEmployeeRoute
import com.hexagon.myemployees.listEmployees.presentation.EmployeesRoute

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Employees.link
    ) {
        composable(Routes.Employees.link) {
            EmployeesRoute(
                navController = navController
            )
        }
        composable(
            route = Routes.EditEmployee.link,
            arguments = listOf(navArgument(Arguments.EMPLOYEE_ID_KEY) { nullable = true })
        ) { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString(Arguments.EMPLOYEE_ID_KEY)
            EditEmployeeRoute(
                navController = navController,
                employeeId = employeeId?.toIntOrNull()
            )
        }
    }
}