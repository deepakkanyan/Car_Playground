package com.carplayground.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun BoxWithDropdowns() {
    var category by remember { mutableStateOf("") }
    var subCategory by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Filter")
            Spacer(modifier = Modifier.height(20.dp))
            Card(Modifier.fillMaxWidth()
                .height(40.dp)) {
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(10) {
                        Text(text = "Category $it")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Card(Modifier.fillMaxWidth()
                .height(40.dp)) {
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(10) {
                        Text(text = "Category $it")
                    }
                }
            }
        }
    }
}
