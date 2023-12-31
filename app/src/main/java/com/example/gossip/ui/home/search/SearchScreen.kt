package com.example.gossip.ui.home.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gossip.model.UserDataModelResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchText: String,
    users: List<UserDataModelResponse.User>,
    onSearchTextChange: (searchText: String) -> Unit,
    onBackArrowClick: (() -> Unit)? = null,
    onClick: (userId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Search User")
                }
            },
            navigationIcon = {
                IconButton(onClick = { onBackArrowClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            },
            modifier = Modifier.height(50.dp)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = { onSearchTextChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(0.dp),
                placeholder = { Text(text = "Search") },
                shape = CircleShape,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (searchText.isNotBlank()) {
                        IconButton(onClick = {
                            onSearchTextChange("")
                        }) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(users) { user ->
                    //                Text(
                    //                    text = user.username!!,
                    //                    modifier = Modifier
                    //                        .fillMaxWidth()
                    //                        .padding(16.dp)
                    //                )
                    SearchItem(user = user, onClick = onClick)
                }
            }

        }
    }
}

@Composable
fun SearchItem(
    user: UserDataModelResponse.User,
    onClick: (userId: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { onClick(user.userId!!) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
//                Spacer(modifier = Modifier.width(4.dp))
//                AsyncImage(
//                    model = R.drawable.baseline_account_circle_24,
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.size(38.dp)
//                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = user.username!!,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = user.phone!!,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchItemPreview() {
    SearchItem(
        user = UserDataModelResponse.User(
            "Yash",
            "1234567890"
        ),
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        searchText = "Yas",
        users = listOf(
            UserDataModelResponse.User(
                "Yash",
                "1234567890"
            ),
            UserDataModelResponse.User(
                "Yash1",
                "1234567987"
            ),

            ),
        onSearchTextChange = {},
        onClick = {}
    )
}
