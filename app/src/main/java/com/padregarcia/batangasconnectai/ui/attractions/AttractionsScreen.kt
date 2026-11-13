package com.padregarcia.batangasconnectai.ui.attractions

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
private val LightBackground = Color(0xFFF7F9F7)
private val DarkText = Color(0xFF24332B)
private val GrayText = Color(0xFF68756D)

data class Attraction(
    val name: String,
    val location: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun AttractionsScreen(onBackClick: () -> Unit) {

    var searchText by remember { mutableStateOf("") }

    val attractions = listOf(
        Attraction(
            name = "Pick & Go Farm",
            location = "Padre Garcia, Batangas",
            description = "Explore agricultural landscapes, farm areas, and outdoor activities.",
            imageUrl = "https://www.wowbatangas.com/wp-content/uploads/2021/05/Pick-Go-Farm-Cawongan-Padre-Garcia-11-1.jpg"
        ),
        Attraction(
            name = "Livestock Auction Market",
            location = "Padre Garcia, Batangas",
            description = "Learn about the town's livestock trading heritage and market activities.",
            imageUrl = "https://www.wowbatangas.com/wp-content/uploads/2017/07/Cattle-Trading-sa-Padre-Garcia-Batangas-10-1.jpg"
        ),
        Attraction(
            name = "Bawi Eco Trail",
            location = "Barangay Bawi, Padre Garcia",
            description = "Discover nature-inspired outdoor scenery and green surroundings.",
            imageUrl = "https://www.wowbatangas.com/wp-content/uploads/2017/07/Bawi-Eco-Trail-sa-Padre-Garcia-3.jpg"
        )
    )

    val filteredAttractions = attractions.filter {
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
            text = "Discover places worth exploring",
            color = GrayText,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Tourist Attractions",
            color = DarkText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search attractions...") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredAttractions.isEmpty()) {
            Text(
                text = "No attractions found.",
                color = GrayText
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredAttractions) { attraction ->
                    AttractionCard(attraction)
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
fun AttractionCard(attraction: Attraction) {

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
                model = attraction.imageUrl,
                contentDescription = attraction.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = attraction.name,
                    color = DarkText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = attraction.location,
                    color = ForestGreen,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = attraction.description,
                    color = GrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}