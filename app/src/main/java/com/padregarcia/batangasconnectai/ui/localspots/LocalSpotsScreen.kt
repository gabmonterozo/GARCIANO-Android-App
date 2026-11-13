package com.padregarcia.batangasconnectai.ui.localspots

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

data class LocalSpot(
    val name: String,
    val location: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun LocalSpotsScreen(onBackClick: () -> Unit) {

    var searchText by remember { mutableStateOf("") }

    val localSpots = listOf(
        LocalSpot(
            name = "Town Center",
            location = "Padre Garcia, Batangas",
            description = "Explore the central area of the municipality and its nearby establishments.",
            imageUrl = "https://mmx.prnewswire.com/media/1926204/Annapolis_Town_Center_new_Annapolis_Town_Center.jpg?p=facebook"
        ),
        LocalSpot(
            name = "Local Market",
            location = "Padre Garcia, Batangas",
            description = "Discover a sample local market setting and everyday community activities.",
            imageUrl = "https://i.ytimg.com/vi/mR2AvQqFN6A/maxresdefault.jpg"
        ),
        LocalSpot(
            name = "Nature Escape",
            location = "Padre Garcia, Batangas",
            description = "Enjoy a sample nature-inspired destination with peaceful green surroundings.",
            imageUrl = "https://campsites.ph/uploads/images/campsite-9-m-balansay-farm-camping-site-f6878c9d27c8febbcfb4553ff389f1fe.jpg"
        )
    )

    val filteredSpots = localSpots.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.location.contains(searchText, ignoreCase = true)
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
            text = "Find interesting places around town",
            color = GrayText
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Local Spots",
            color = DarkText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search local spots...") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredSpots.isEmpty()) {
            Text(
                text = "No local spots found.",
                color = GrayText
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredSpots) { spot ->
                    LocalSpotCard(spot)
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
fun LocalSpotCard(spot: LocalSpot) {

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
                model = spot.imageUrl,
                contentDescription = spot.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = spot.name,
                    color = DarkText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = spot.location,
                    color = ForestGreen,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = spot.description,
                    color = GrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}