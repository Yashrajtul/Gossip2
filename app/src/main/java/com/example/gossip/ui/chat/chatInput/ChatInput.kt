package com.example.gossip.ui.chat.chatInput

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
fun ChatInputPreview() {
    ChatInput(onMessageChange = {}, onFocusEvent = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ChatInput(
    modifier: Modifier = Modifier,
    onMessageChange: (String) -> Unit,
    onFocusEvent: (Boolean) -> Unit
) {

    val context = LocalContext.current

    var input by remember { mutableStateOf(TextFieldValue("")) }
    val textEmpty: Boolean by derivedStateOf { input.text.isEmpty() }

//    val imePaddingValues = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.ime)
//    val imeBottomPadding = imePaddingValues.calculateBottomPadding().value.toInt()
    val imePaddingValues = PaddingValues()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Bottom
    ) {

//        ChatTextField(
//            modifier = modifier.weight(1f).focusable(true),
//            input = input,
//            empty = textEmpty,
//            onValueChange = {
//                input = it
//            }, onFocusEvent = {
//                onFocusEvent(it)
//            }
//        )
//
//        Spacer(modifier = Modifier.width(6.dp))
        TextField(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .weight(1f)
                .focusable(true),
//                .padding(bottom = MaterialTheme.spacing.extraSmall),
            value = input,
            onValueChange = { input = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Message")
            },
            leadingIcon = {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Emoji Clicked.\n(Not Available)",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Mood, contentDescription = "Mood")
                }
            },
            trailingIcon = {
                Row() {
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "Attach File Clicked.\n(Not Available)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(imageVector = Icons.Filled.AttachFile, contentDescription = "File")
                    }
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "Camera Clicked.\n(Not Available)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = "Camera")
                    }
                }

            }

        )
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                if (!textEmpty) {
                    onMessageChange(input.text)
                    input = TextFieldValue("")
                } else {
                    Toast.makeText(
                        context,
                        "Sound Recorder Clicked.\n(Not Available)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Icon(
                imageVector = if (textEmpty) Icons.Filled.Mic else Icons.Filled.Send,
//                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
        }
    }
}