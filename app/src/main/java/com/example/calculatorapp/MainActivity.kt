package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
               Surface (
                   modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.background
               ){
                   Calculator()
               }
            }
        }
    }
}

@Composable
fun Calculator() {
    var display by remember { mutableStateOf("0") }
    var operand by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var isNewNumber by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "0",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(16.dp))

        val buttons = listOf(
            listOf("C", "±", "%", "÷"),
            listOf("7", "8", "9", "×"),
            listOf("4", "5", "6", "−"),
            listOf("1", "2", "3", "+"),
            listOf("0", ".", "=")
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            buttons.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { button ->
                        val weight = if(button == "0") 2f else 1f
                        val backgroundColor  = when (button) {
                            in listOf("÷", "×", "−", "+", "=") -> MaterialTheme.colorScheme.primary
                            in listOf("C", "±", "%") -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.inversePrimary
                        }
                        val contentColor = when (button) {
                            in listOf("÷", "×", "−", "+", "=") -> MaterialTheme.colorScheme.onPrimary
                            in listOf("C", "±", "%") -> MaterialTheme.colorScheme.onSecondary
                            else -> MaterialTheme.colorScheme.onPrimary
                        }
                       Button(
                           onClick = { },
                           modifier = Modifier
                               .weight(weight)
                               .aspectRatio(if(button == "0") 2f else 1f),
                           colors = ButtonDefaults.buttonColors(
                               containerColor = backgroundColor ,
                               contentColor = contentColor
                           ),
                           shape = RoundedCornerShape(16.dp)
                       ) {
                           Text(
                               text = button,
                               fontSize = 30.sp,
                               fontWeight = FontWeight.Medium
                           )
                       }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorAppTheme {
       Calculator()
    }
}