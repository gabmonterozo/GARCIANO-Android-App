package com.padregarcia.batangasconnectai.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Register a new user
    fun register(
        fullName: String,
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val firebaseUser = task.result?.user

                    if (firebaseUser != null) {

                        val profileUpdates =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(fullName)
                                .build()

                        firebaseUser.updateProfile(profileUpdates)
                            .addOnCompleteListener { updateTask ->

                                if (updateTask.isSuccessful) {
                                    callback(
                                        true,
                                        "Account created successfully!"
                                    )
                                } else {
                                    callback(
                                        true,
                                        "Account created, but profile name could not be updated."
                                    )
                                }
                            }

                    } else {
                        callback(
                            true,
                            "Account created successfully!"
                        )
                    }

                } else {
                    callback(
                        false,
                        task.exception?.localizedMessage
                            ?: "Registration failed."
                    )
                }
            }
    }

    // Log in an existing user
    fun login(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    callback(true, "Login successful!")
                } else {
                    callback(
                        false,
                        task.exception?.localizedMessage
                            ?: "Login failed."
                    )
                }
            }
    }

    // Log out the current user
    fun logout() {
        auth.signOut()
    }

    // Check if a user is currently logged in
    fun getCurrentUser() = auth.currentUser
}
