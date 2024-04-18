package com.hexagon.myemployees.listEmployees.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hexagon.myemployees.core.domain.models.Employee
import com.hexagon.myemployees.listEmployees.presentation.EmployeesActions

@Composable
fun EmployeesLazyColumn(
    employees: List<Employee>,
    actions: EmployeesActions,
) {
    val scrollState = rememberScrollableState { it }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        LazyColumn(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(employees) {
                EmployeeCard(
                    employee = it,
                    onEdit = actions.editEmployee,
                    onDelete = actions.deleteEmployee,
                    onToggleIsActive = actions.toggleIsActive
                )
            }
        }
    }
}
