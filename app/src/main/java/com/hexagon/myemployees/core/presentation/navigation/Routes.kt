package com.hexagon.myemployees.core.presentation.navigation

sealed class Routes(val link: String) {
    data object Employees: Routes("employees")
    data object EditEmployee: Routes("edit_employee/{${Arguments.EMPLOYEE_ID_KEY}}") {
        fun linkWithEmployeeId(employeeId: Int? = null) = link.replace("{${Arguments.EMPLOYEE_ID_KEY}}", employeeId?.toString() ?: "null")
    }
}