package com.hexagon.myemployees.editEmployee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hexagon.myemployees.core.presentation.theme.MyEmployeesTheme

@Preview
@Composable
fun MainButtonPreview() {
    MyEmployeesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val buttonState = true
            PrimaryMainButton(
                modifier = Modifier.width(200.dp),
                buttonText = "Botão primário",
                isButtonEnabled = buttonState
            ) {

            }
            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}

@Composable
fun PrimaryMainButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    isButtonEnabled: Boolean,
    onClick: () -> Unit,
) {
    val disabledColor = MaterialTheme.colorScheme.onBackground
    val roundedShape = RoundedCornerShape(30.dp)
    Button(
        modifier = modifier
            .clip(roundedShape)
            .height(44.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(disabledContainerColor = disabledColor),
        enabled = isButtonEnabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = buttonText, style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
        }
    }
}