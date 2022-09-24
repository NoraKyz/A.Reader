package com.example.areader.screens.login

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    //val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("FB", "signInWithEmailAndPassword: successful ${it.result}")
                            home()
                        } else {
                            Log.d("FB", "signInWithEmailAndPassword: ${it.result}")
                        }

                    }

            } catch (ex: Exception) {
                Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
            }
        }

    fun createUserWithEmailAndPassword(
        displayName: String,
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        home()
                        createUser(displayName)
                    } else {
                        Log.d("FB", "createUserWithEmailAndPassword: ${it.result}")
                    }

                    _loading.value = false
                }
        }

    }

    fun forgotPasswordWithEmail(email: String, home: () -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    home()
                } else {
                    Log.d("FB", "sendPasswordResetEmail: ${it.result}")
                }
            }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

        val user = MUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer",
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}