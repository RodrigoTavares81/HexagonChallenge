package com.hexagon.myemployees.listEmployees.presentation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class EmployeesScreenTabs(
    val title: String,
    val icon: ImageVector,
) {
    data object ActiveEmployees: EmployeesScreenTabs(
        title = "Active",
        icon = Icons.Default.Done,
    )
    data object InactiveEmployees: EmployeesScreenTabs(
        title = "Inactive",
        icon = Icons.Default.Warning
    )
}