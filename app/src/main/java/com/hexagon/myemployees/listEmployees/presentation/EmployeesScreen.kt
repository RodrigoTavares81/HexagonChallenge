package com.hexagon.myemployees.listEmployees.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hexagon.myemployees.listEmployees.presentation.components.EmployeesLazyColumn
import com.hexagon.myemployees.listEmployees.presentation.components.getRandomEmployee
import com.hexagon.myemployees.listEmployees.presentation.tabs.EmployeesScreenTabs


@Composable
fun EmployeesScreen(
    state: EmployeesState,
    actions: EmployeesActions,
) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(EmployeesScreenTabs.ActiveEmployees, EmployeesScreenTabs.InactiveEmployees)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = actions.addNewEmployee,
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = null) }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                TabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, tab ->
                        val currentTab = tabs[index]
                        Tab(
                            text = { Text(currentTab.title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            icon = {
                                Icon(
                                    imageVector = currentTab.icon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> ActiveEmployees(state, actions)
                    1 -> InactiveEmployees(state, actions)
                }
            }
        }
    )
}

@Composable
fun ActiveEmployees(
    state: EmployeesState,
    actions: EmployeesActions,
) {
    EmployeesLazyColumn(
        employees = state.employees.filter { it.isActive },
        actions = actions,
    )
}

@Composable
fun InactiveEmployees(
    state: EmployeesState,
    actions: EmployeesActions,
) {
    EmployeesLazyColumn(
        employees = state.employees.filter { it.isActive.not() },
        actions = actions,
    )
}

@Composable
@Preview(name = "Employees")
private fun EmployeesScreenPreview() {
    EmployeesScreen(
        state = EmployeesState(
            listOf(
                getRandomEmployee(),
                getRandomEmployee(),
            )
        ),
        actions = EmployeesActions(
            addNewEmployee = {},
            editEmployee = {},
            deleteEmployee = {},
            toggleIsActive = {},
        )
    )
}
