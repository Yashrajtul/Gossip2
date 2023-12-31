package com.example.gossip.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.gossip.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    context: Context = LocalContext.current,
    onClick: (name: String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "GOSSIP")
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
//                navigationIcon = {
//                    IconButton(
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Menu"
//                        )
//                    }
//                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        properties = PopupProperties(focusable = true)
                    ) {

                        DropdownMenuItem(text = {
                            Text(text = "GroupChat")
                        }, onClick = {
                            Toast.makeText(context, "GroupChat", Toast.LENGTH_SHORT).show()
                            showMenu = false
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Settings")
                        }, onClick = {
                            Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                            showMenu = false
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Help")
                        }, onClick = {
                            Toast.makeText(context, "Help", Toast.LENGTH_SHORT).show()
                            showMenu = false
                        })

                    }
                }
            )
        }
    ) {
        MainChats(modifier = Modifier.padding(it), onClick = onClick)
    }
}


@Composable
fun MainChats(
//    chat : List<Chat> = chats,
    modifier: Modifier,
    onClick: (name: String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(9) {
            ChatUnit(it.toString(), onClick)
        }
    }
}

@Composable
fun ChatUnit(
//    chat : Chat,
    text: String,
    onClick: (name: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable {
//                onClick(chat.name)
            }
            .padding(horizontal = 4.dp)
    ) {
        Spacer(modifier = Modifier.padding(6.dp))
        Image(
//            painter = painterResource(id = chat.image),
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxHeight(.75f)
                .clickable {

                }
        )
        Spacer(modifier = Modifier.padding(4.dp))
//        Text(text = chat.name)
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatUnit() {
    ChatUnit(text = "Yash", onClick = { })
}

@Preview(showBackground = true)
@Composable
fun MainChatsPreiview() {
    MainChats(modifier = Modifier, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreiview() {
    MainScreen(onClick = {})
}
