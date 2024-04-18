package com.hexagon.myemployees.editEmployee.presentation

import android.content.ContentResolver
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContentResolverCompat
import coil.compose.AsyncImage
import com.hexagon.myemployees.R
import com.hexagon.myemployees.core.domain.models.Employee
import com.hexagon.myemployees.editEmployee.presentation.components.PrimaryMainButton
import com.hexagon.myemployees.listEmployees.presentation.components.getRandomEmployee

@Composable
fun EditEmployeeScreen(
    state: EditEmployeeState,
    actions: EditEmployeeActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(state.employeeData, actions.onPhotographURIChanged)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("name") },
            value = state.employeeData.name,
            onValueChange = actions.onNameChanged
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("cpf") },
            value = state.employeeData.cpf,
            onValueChange = actions.onCPFChanged
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("city") },
            value = state.employeeData.city,
            onValueChange = actions.onCityChanged
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),

            label = { Text("birthDate") },
            value = state.employeeData.birthDate,
            onValueChange = actions.onBirthDateChanged
        )
        PrimaryMainButton(buttonText = "Salvar", isButtonEnabled = true) {
            actions.onSave.invoke(state.employeeData)
        }
    }
}

@Composable
fun ProfilePicture(
    employee: Employee,
    onPhotographURIChanged: (String) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        it?.run {
            onPhotographURIChanged.invoke(this.toString())
            object: ContentResolver(context) {
                init {
                    takePersistableUriPermission(this@run, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            }
        }
    }
    ConstraintLayout(modifier = Modifier.clickable {
        launcher.launch("image/*")
    }) {
        val (circle, icon) = createRefs()
        Box(modifier = Modifier
            .background(Color.White, shape = CircleShape)
            .clip(CircleShape)
            .wrapContentSize()
            .constrainAs(circle) { centerTo(parent) }) {
            AsyncImage(
                model = employee.photographURI,
                contentDescription = "",
                error = painterResource(id = R.drawable.baseline_person_24),
                onError = {
                    it.result.throwable
                },
                contentScale = ContentScale.Crop,
                clipToBounds = true,
                modifier = Modifier.size(200.dp)
            )
        }
        Box(
            Modifier
                .clip(CircleShape)
                .size(56.dp)
                .background(Color.White)
                .constrainAs(icon) {
                    bottom.linkTo(circle.bottom)
                    end.linkTo(circle.end)
                }, contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .paint(
                        painterResource(id = R.drawable.baseline_add_24),
                        colorFilter = ColorFilter.tint(Color.DarkGray),
                        contentScale = ContentScale.FillBounds,
                    )
            )
        }
    }
}

@Composable
@Preview(name = "EditEmployees")
private fun EditEmployeesScreenPreview() {
    EditEmployeeScreen(
        state = EditEmployeeState(
            getRandomEmployee(),
        ), actions = EditEmployeeActions()
    )
}

