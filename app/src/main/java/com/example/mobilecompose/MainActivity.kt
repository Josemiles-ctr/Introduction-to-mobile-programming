package com.example.mobilecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mobilecompose.ui.theme.MobileComposeTheme

// Single entry-point activity; hosts the entire Compose UI tree.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Install splash screen to animate the splash screen exit.
        installSplashScreen()
        // Renders content behind system bars for a full-bleed, edge-to-edge look.
        enableEdgeToEdge()
        setContent {
            MobileComposeTheme {
                // Scaffold manages insets; innerPadding prevents content from sitting
                // under the status/navigation bars.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
/**
 * Main screen composable — a card-style form that greets the user by name.
 *
 * State is kept local here because it doesn't need to be shared with other screens.
 * If this grows, consider hoisting [inputName] and [welcomeMessage] to a ViewModel.
 */
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    // Holds the live value of the text field.
    var inputName by remember { mutableStateOf("") }
    // Holds the result message shown after submission.
    var welcomeMessage by remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Elevated card container for the form content.
            Surface(
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                tonalElevation = 4.dp,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Text input field
                    OutlinedTextField(
                        value = inputName,
                        onValueChange = { inputName = it },
                        label = { Text("Enter a Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Buttons Row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Button(
                            onClick = {
                                // Show an error hint when the field is blank.
                                welcomeMessage = if (inputName.isNotEmpty()) {
                                    "Welcome $inputName!"
                                } else {
                                    "Please enter a name"
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFC5D86D)
                            )
                        ) {
                            Text("Submit", color = Color.Black)
                        }

                        Button(
                            onClick = {
                                // Reset both state values to their initial empty state.
                                welcomeMessage = ""
                                inputName = ""
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFC5D86D)
                            )
                        ) {
                            Text("Clear", color = Color.Black)
                        }
                    }

                    // Welcome message display — only rendered when there is a message to show.
                    if (welcomeMessage.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = welcomeMessage,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

// Design-time preview — visible in Android Studio's Preview pane without a running device.
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileComposeTheme {
        Greeting()
    }
}