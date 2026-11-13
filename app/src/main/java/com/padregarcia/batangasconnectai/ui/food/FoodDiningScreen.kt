package com.padregarcia.batangasconnectai.ui.food

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

data class FoodItem(
    val name: String,
    val category: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun FoodDiningScreen(onBackClick: () -> Unit) {

    var searchText by remember { mutableStateOf("") }

    val foodItems = listOf(
        FoodItem(
            name = "Batangas Lomi",
            category = "Noodles",
            description = "A thick and hearty noodle dish with a flavorful broth and toppings.",
            imageUrl = "https://images.deliveryhero.io/image/fd-ph/LH/p2k0-listing.jpg"
        ),
        FoodItem(
            name = "Bulalo",
            category = "Filipino Soup",
            description = "A comforting beef soup traditionally served with vegetables.",
            imageUrl = "https://mamitasfilipinocuisine.com/wp-content/uploads/2020/08/bulalo-1-scaled.jpg?x18516"
        ),
        FoodItem(
            name = "Pancit",
            category = "Noodles",
            description = "A popular Filipino noodle dish often served during gatherings.",
            imageUrl = "https://i.pinimg.com/originals/f7/4d/db/f74ddb0f64e5d42805e94faa02ff4b69.jpg"
        ),
        FoodItem(
            name = "Halo-Halo",
            category = "Dessert",
            description = "A refreshing Filipino dessert made with crushed ice and sweet ingredients.",
            imageUrl = "https://images.deliveryhero.io/image/fd-ph/LH/xhtr-listing.jpg"
        )
    )

    val filteredFoods = foodItems.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.category.contains(searchText, ignoreCase = true)
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
            text = "Discover local flavors and Filipino favorites",
            color = GrayText
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Food & Dining",
            color = DarkText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search food...") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredFoods.isEmpty()) {
            Text(
                text = "No food items found.",
                color = GrayText
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredFoods) { food ->
                    FoodCard(food)
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
fun FoodCard(food: FoodItem) {

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
                model = food.imageUrl,
                contentDescription = food.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = food.name,
                    color = DarkText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = food.category,
                    color = ForestGreen,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = food.description,
                    color = GrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}