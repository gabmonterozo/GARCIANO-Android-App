package com.padregarcia.batangasconnectai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import com.padregarcia.batangasconnectai.data.repository.AuthRepository
import com.padregarcia.batangasconnectai.ui.auth.LoginScreen
import com.padregarcia.batangasconnectai.ui.auth.RegisterScreen
import com.padregarcia.batangasconnectai.ui.dashboard.DashboardScreen
import com.padregarcia.batangasconnectai.ui.attractions.AttractionsScreen
import com.padregarcia.batangasconnectai.ui.food.FoodDiningScreen
import com.padregarcia.batangasconnectai.ui.events.EventsScreen
import com.padregarcia.batangasconnectai.ui.localspots.LocalSpotsScreen

private val ForestGreen = Color(0xFF245C45)
private val LightGreen = Color(0xFFE8F1EB)
private val BackgroundColor = Color(0xFFF7F9F7)
private val DarkText = Color(0xFF24332B)
private val GrayText = Color(0xFF68756D)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                BatangasConnectApp()
            }
        }
    }
}

@Composable
fun BatangasConnectApp() {

    val authRepository = remember {
        AuthRepository()
    }

    var currentScreen by remember {
        mutableStateOf("welcome")
    }

    var userName by remember {
        mutableStateOf("")
    }

    when (currentScreen) {

        "welcome" -> {
            WelcomeScreen(
                onStartClick = {
                    currentScreen = "login"
                }
            )
        }

        "login" -> {
            LoginScreen(
                onLogin = { email, password ->

                    authRepository.login(
                        email.trim(),
                        password
                    ) { success, message ->

                        if (success) {
                            userName = authRepository
                                .getCurrentUser()
                                ?.displayName
                                ?: email.substringBefore("@")

                            currentScreen = "dashboard"
                        }
                    }
                },
                onRegisterClick = {
                    currentScreen = "register"
                }
            )
        }

        "register" -> {
            RegisterScreen(
                onRegister = { fullName, email, password, callback ->

                    authRepository.register(
                        fullName,
                        email,
                        password
                    ) { success, message ->

                        callback(success, message)
                    }
                },
                onLoginClick = {
                    currentScreen = "login"
                }
            )
        }

        "dashboard" -> {
            DashboardScreen(
                userName = userName,
                onLogout = {
                    authRepository.logout()
                    userName = ""
                    currentScreen = "welcome"
                },
                onAttractionsClick = {
                    currentScreen = "attractions"
                },
                onFoodDiningClick = {
                    currentScreen = "food"
                },
                onEventsClick = {
                    currentScreen = "events"
                },
                onLocalSpotsClick = {
                    currentScreen = "localspots"
                }
            )
        }

        "attractions" -> {
            AttractionsScreen(
                onBackClick = {
                    currentScreen = "dashboard"
                }
            )
        }

        "food" -> {
            FoodDiningScreen(
                onBackClick = {
                    currentScreen = "dashboard"
                }
            )
        }

        "events" -> {
            EventsScreen(
                onBackClick = {
                    currentScreen = "dashboard"
                }
            )
        }

        "localspots" -> {
            LocalSpotsScreen(
                onBackClick = {
                    currentScreen = "dashboard"
                }
            )
        }
    }
}

// Welcome screen
@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "GARCIANO",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ForestGreen,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "DISCOVER • EXPLORE • EXPERIENCE",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = GrayText,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = ForestGreen
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌿",
                    fontSize = 55.sp
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Discover Padre Garcia",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Explore the beauty, culture, food, " +
                            "and hidden destinations of Padre Garcia, Batangas.",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Your Local Travel Companion",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Plan your next adventure and discover " +
                    "interesting places within the municipality.",
            fontSize = 14.sp,
            color = GrayText,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        FeatureCard(
            "📍",
            "Local Destinations",
            "Discover attractions and notable places."
        )

        Spacer(modifier = Modifier.height(12.dp))

        FeatureCard(
            "🍽️",
            "Local Food",
            "Explore food spots and local favorites."
        )

        Spacer(modifier = Modifier.height(12.dp))

        FeatureCard(
            "🎉",
            "Events and Culture",
            "Learn about local activities and traditions."
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onStartClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen
            )
        ) {
            Text(
                text = "Start Exploring",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Discover more. Experience Padre Garcia.",
            fontSize = 12.sp,
            color = GrayText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        HorizontalDivider(color = LightGreen)

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "GARCIANO • 2026",
            fontSize = 12.sp,
            color = GrayText
        )

        Text(
            text = "Padre Garcia, Batangas, Philippines",
            fontSize = 11.sp,
            color = GrayText
        )

        Spacer(modifier = Modifier.height(15.dp))
    }
}

// Reusable feature card
@Composable
fun FeatureCard(
    icon: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = GrayText
                )
            }
        }
    }
}

// Welcome screen preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BatangasConnectPreview() {
    MaterialTheme {
        WelcomeScreen(onStartClick = {})
    }
}