package com.padregarcia.batangasconnectai.ui.events

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private val ForestGreen = Color(0xFF245C45)
private val DarkText = Color(0xFF24332B)
private val GrayText = Color(0xFF68756D)

data class LocalEvent(
    val name: String,
    val date: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun EventsScreen(onBackClick: () -> Unit) {

    var searchText by remember { mutableStateOf("") }

    val events = listOf(
        LocalEvent(
            name = "Town Fiesta",
            date = "Schedule to be announced",
            description = "A community celebration featuring local traditions, activities, and gatherings.",
            imageUrl = "https://live.staticflickr.com/4132/5202690851_02e23fb610_b.jpg"
        ),
        LocalEvent(
            name = "Community Festival",
            date = "Schedule to be announced",
            description = "A sample community event highlighting local culture and entertainment.",
            imageUrl = "https://wowbatangas.com/wp-content/uploads/2012/12/IMG_4351-Copy.jpg"
        ),
        LocalEvent(
            name = "Local Cultural Activities",
            date = "Schedule to be announced",
            description = "Discover activities that celebrate community participation and local heritage.",
            imageUrl = "https://www.wowbatangas.com/wp-content/uploads/2021/12/Padre-Garcia-72nd-Founding-Anniversary-Kabakahan-Festival-2021-24.jpg"
        )
    )

    val filteredEvents = events.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.description.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "GARCIANO",
            color = ForestGreen,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Discover community activities and culture",
            color = GrayText
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Events & Culture",
            color = DarkText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search events...") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredEvents.isEmpty()) {
            Text(
                text = "No events found.",
                color = GrayText
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredEvents) { event ->
                    EventCard(event)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun EventCard(event: LocalEvent) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column {

            AsyncImage(
                model = event.imageUrl,
                contentDescription = event.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = event.name,
                    color = DarkText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = event.date,
                    color = ForestGreen,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = event.description,
                    color = GrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}