package com.hexagon.myemployees.editEmployee.presentation

import android.net.Uri
import com.hexagon.myemployees.core.domain.models.Employee

data class EditEmployeeState(
    val employeeData: Employee = Employee(
        name = "",
        birthDate = "",
        cpf = "",
        city = "",
        photographURI = "",
        isActive = false
    )
)


data class EditEmployeeActions(
    val onNameChanged: (String) -> Unit = {},
    val onBirthDateChanged: (String) -> Unit = {},
    val onCPFChanged: (String) -> Unit = {},
    val onCityChanged: (String) -> Unit = {},
    val onPhotographURIChanged: (String) -> Unit = {},
    val onIsActiveChanged: (Boolean) -> Unit = {},
    val onSave: (Employee) -> Unit = {}
)