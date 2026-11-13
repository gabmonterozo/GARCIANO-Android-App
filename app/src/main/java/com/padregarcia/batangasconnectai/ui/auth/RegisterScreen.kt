package com.padregarcia.batangasconnectai.ui.auth

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val ForestGreen = Color(0xFF245C45)
private val BackgroundColor = Color(0xFFF7F9F7)
private val DarkText = Color(0xFF24332B)
private val GrayText = Color(0xFF68756D)

@Composable
fun RegisterScreen(
    onRegister: (
        String,
        String,
        String,
        (Boolean, String?) -> Unit
    ) -> Unit = { _, _, _, callback ->
        callback(false, "Registration is unavailable.")
    },
    onLoginClick: () -> Unit = {}
) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
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

        Spacer(modifier = Modifier.height(30.dp))

        // Registration heading
        Text(
            text = "Create Account",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Join GARCIANO and start exploring.",
            fontSize = 14.sp,
            color = GrayText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Full name field
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            placeholder = { Text("Enter your full name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Create a password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirm password field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            placeholder = { Text("Re-enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ForestGreen,
                focusedLabelColor = ForestGreen,
                cursorColor = ForestGreen
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Success or error message
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = if (isSuccess) ForestGreen else Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Register button
        Button(
            onClick = {

                message = when {
                    fullName.isBlank() ->
                        "Please enter your full name."

                    email.isBlank() ||
                            !android.util.Patterns.EMAIL_ADDRESS
                                .matcher(email.trim()).matches() ->
                        "Please enter a valid email address."

                    password.length < 6 ->
                        "Password must be at least 6 characters."

                    password != confirmPassword ->
                        "Passwords do not match."

                    else -> ""
                }

                if (message.isEmpty()) {

                    isLoading = true
                    isSuccess = false

                    onRegister(
                        fullName.trim(),
                        email.trim(),
                        password
                    ) { success, resultMessage ->

                        isLoading = false
                        isSuccess = success
                        message = resultMessage
                            ?: if (success) {
                                "Account created successfully!"
                            } else {
                                "Registration failed."
                            }
                    }
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen
            )
        ) {

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Create Account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Login navigation
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Already have an account? ",
                fontSize = 13.sp,
                color = GrayText
            )

            TextButton(
                onClick = onLoginClick,
                enabled = !isLoading
            ) {
                Text(
                    text = "Login",
                    color = ForestGreen,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

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
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}
