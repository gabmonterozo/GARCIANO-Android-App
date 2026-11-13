package com.padregarcia.batangasconnectai.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    userName: String,
    onLogout: () -> Unit,
    onAttractionsClick: () -> Unit,
    onFoodDiningClick: () -> Unit,
    onEventsClick: () -> Unit,
    onLocalSpotsClick: () -> Unit
) {

    val green = Color(0xFF245C45)
    val lightGreen = Color(0xFFE8F1EB)
    val background = Color(0xFFF7F9F7)
    val darkText = Color(0xFF24332B)
    val grayText = Color(0xFF68756D)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // App Header
        Text(
            text = "GARCIANO",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = green
        )

        Text(
            text = "DISCOVER • EXPLORE • EXPERIENCE",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = grayText
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Welcome Section
        Text(
            text = "Welcome, $userName!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = darkText
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Discover something new in Padre Garcia.",
            fontSize = 14.sp,
            color = grayText
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = green
            )
        ) {
            Column(
                modifier = Modifier.padding(22.dp)
            ) {
                Text(
                    text = "EXPLORE PADRE GARCIA",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD5E8DA)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Experience the beauty of our town.",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Discover local attractions, delicious food, " +
                            "community events, and hidden gems.",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Explore Categories
        Text(
            text = "Explore Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = darkText
        )

        Spacer(modifier = Modifier.height(14.dp))

        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CategoryCard(
                title = "Attractions",
                description = "Places to visit",
                color = lightGreen,
                modifier = Modifier.weight(1f),
                onClick = onAttractionsClick
            )

            CategoryCard(
                title = "Food & Dining",
                description = "Local food spots",
                color = lightGreen,
                modifier = Modifier.weight(1f),
                onClick = onFoodDiningClick
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CategoryCard(
                title = "Events",
                description = "Local activities",
                color = lightGreen,
                modifier = Modifier.weight(1f),
                onClick = onEventsClick
            )

            CategoryCard(
                title = "Local Spots",
                description = "Discover more",
                color = lightGreen,
                modifier = Modifier.weight(1f),
                onClick = onLocalSpotsClick
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // About Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "About GARCIANO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = green
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your smart tourism companion for discovering " +
                            "the places, culture, and experiences of " +
                            "Padre Garcia, Batangas.",
                    fontSize = 14.sp,
                    color = grayText,
                    lineHeight = 21.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = green
            )
        ) {
            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// Reusable Category Card
@Composable
fun CategoryCard(
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(115.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF245C45)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0xFF68756D)
            )
        }
    }
}