package com.padregarcia.batangasconnectai.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

private val ForestGreen = Color(0xFF245C45)
private val BackgroundColor = Color(0xFFF7F9F7)
private val DarkText = Color(0xFF24332B)
private val GrayText = Color(0xFF68756D)

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // GARCIANO branding
        Text(
            text = "GARCIANO",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ForestGreen,
            letterSpacing = 3.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "DISCOVER • EXPLORE • EXPERIENCE",
            fontSize = 10.sp,
            color = GrayText,
            letterSpacing = 1.5.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Login heading
        Text(
            text = "Welcome Back!",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign in to continue your journey.",
            fontSize = 14.sp,
            color = GrayText
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Login button
        Button(
            onClick = {
                onLogin(email.trim(), password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen
            )
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Registration navigation
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 13.sp,
                color = GrayText
            )

            TextButton(onClick = onRegisterClick) {
                Text(
                    text = "Register",
                    color = ForestGreen,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Discover more. Experience Padre Garcia.",
            fontSize = 12.sp,
            color = GrayText,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}
