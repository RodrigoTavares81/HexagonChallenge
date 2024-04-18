package com.hexagon.myemployees.listEmployees.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.hexagon.myemployees.R
import com.hexagon.myemployees.core.presentation.theme.MyEmployeesTheme
import com.hexagon.myemployees.core.domain.models.Employee

@Composable
fun EmployeeCard(
    employee: Employee,
    onEdit: (employeeId: Int) -> Unit,
    onDelete: (employeeId: Int) -> Unit,
    onToggleIsActive: (employeeId: Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.Gray, RoundedCornerShape(12.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            val (profilePicture, name, deleteButton, toggleIsActiveButton, editButton) = createRefs()
            AsyncImage(
                model = employee.photographURI,
                error = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
                    .constrainAs(profilePicture) {
                        centerVerticallyTo(parent)
                    }
            )
            Text(
                text = employee.name,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(profilePicture.top)
                    start.linkTo(profilePicture.end, margin = 12.dp)
                }
            )
            Icon(
                imageVector = if (employee.isActive) Icons.Default.CheckCircle else Icons.Filled.Warning,
                contentDescription = null,
                tint = if (employee.isActive) LocalContentColor.current else Color.Red,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        onToggleIsActive.invoke(employee.id!!)
                    }
                    .constrainAs(toggleIsActiveButton) {
                        centerVerticallyTo(parent)
                        end.linkTo(editButton.start, 32.dp)
                    }
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = if (employee.isActive) {
                    LocalContentColor.current
                } else {
                    Color.Red
                },
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onEdit.invoke(employee.id!!) }
                    .constrainAs(editButton) {
                        centerVerticallyTo(parent)
                        end.linkTo(deleteButton.start, 32.dp)
                    }
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = LocalContentColor.current,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onDelete.invoke(employee.id!!) }
                    .constrainAs(deleteButton) {
                        centerVerticallyTo(parent)
                        end.linkTo(parent.end, 8.dp)
                    }
            )

        }
    }
}

@Preview
@Composable
private fun EmployeeCardPreview() {
    MyEmployeesTheme {
        EmployeeCard(
            employee = getRandomEmployee(),
            onEdit = {},
            onDelete = {},
            onToggleIsActive = {},
        )
    }
}

fun getRandomEmployee(): Employee = Employee(
    name = "Rodrigo",
    birthDate = "10/05/2000",
    cpf = "579123849",
    city = "Rio de janeiro",
    photographURI = "fotografia",
    isActive = false
)