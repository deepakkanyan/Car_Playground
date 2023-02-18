package com.carplayground.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carplayground.room.Car

@Composable
fun BoxWithDropdowns(carList: List<Car>, onSelectedFilter: (String)->Unit ) {
    var selectedMake by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }

    var mExpanded1 by remember { mutableStateOf(false) }
    var mExpanded2 by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
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
            Card(
                Modifier
                    .clickable(onClick = { mExpanded1 = true })
                    .fillMaxWidth()
                    .height(40.dp)) {
                Text(text = selectedMake,Modifier.align(Alignment.Start).height(40.dp))
                DropdownMenu(
                    expanded = mExpanded1,
                    onDismissRequest = { mExpanded1 = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    carList.map { it.make }.forEach  {
                        DropdownMenuItem(text = {  Text(text = it) }, onClick = {
                            selectedMake = it
                            mExpanded1 = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { mExpanded2 = true })
                    .height(40.dp)) {

              DropdownMenu(
                    expanded = mExpanded2,
                    onDismissRequest = {mExpanded2 = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                  carList.filter { it.make == selectedMake }.forEach  {
                        DropdownMenuItem(text = {  Text(text =  it.model) }, onClick = {
                            selectedModel = it.model
                            mExpanded2 = false
                            onSelectedFilter.invoke(selectedModel)
                        })
                    }
                }
                Text(text = selectedModel,Modifier.align(Alignment.Start).height(40.dp), textAlign = TextAlign.Center)

            }
        }
    }
}

