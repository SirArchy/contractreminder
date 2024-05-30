package com.example.contractreminder

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate

data class Contract(
    val name: String,
    val logo: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val renewalDate: LocalDate,
    val nextCancelableDate: LocalDate
)

@androidx.compose.runtime.Composable
fun ContractReminderApp(navController: NavController, context: Context) {
    val (contracts, setContracts) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            listOf<Contract>()
        )
    }

    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { androidx.compose.material3.Text("Contract Reminder") },
                actions = {
                    androidx.compose.material3.IconButton(onClick = { /* Navigate to add contract screen */ }) {
                        androidx.compose.material3.Icon(
                            Icons.Filled.Add,
                            contentDescription = "Add Contract"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(contracts) { contract ->
                ContractCard(contract, navController, context)
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun ContractCard(contract: Contract, navController: NavController, context: Context) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Navigate to contract details screen */ },
        elevation = 4.dp
    ) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.padding(16.dp)) {
            androidx.compose.foundation.layout.Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = contract.logo),
                    contentDescription = contract.name,
                    modifier = Modifier.size(48.dp)
                )
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
                androidx.compose.material3.Text(
                    text = contract.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))
            androidx.compose.material3.Text(
                text = "Start Date: ${contract.startDate}",
                color = Color.Gray
            )
            androidx.compose.material3.Text(
                text = "End Date: ${contract.endDate}",
                color = Color.Gray
            )
            androidx.compose.material3.Text(
                text = "Renewal Date: ${contract.renewalDate}",
                color = Color.Gray
            )
            androidx.compose.material3.Text(
                text = "Next Cancelable Date: ${contract.nextCancelableDate}",
                color = Color.Gray
            )
        }
    }
}