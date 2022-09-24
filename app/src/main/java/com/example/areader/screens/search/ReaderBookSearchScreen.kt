package com.example.areader.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.areader.components.ReaderSearchAppBar
import com.example.areader.components.SearchInput
import com.example.areader.model.MBook

@Preview
@Composable
fun SearchScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        ReaderSearchAppBar(
            title = "Search Book",
            navController = navController
        )
    }) {
        Surface(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            SearchForm(
                modifier = Modifier.padding(10.dp),
                navController = NavController(LocalContext.current)
            ) {

            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    navController: NavController,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {

    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState) {
        searchQueryState.value.isNotEmpty()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchInput(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            nameState = searchQueryState,
            onAction = KeyboardActions(onDone = { keyboardController?.hide() }) {
                searchQueryState.value.trim()
            }
        )

        BookList(navController)
    }
}

@Composable
fun BookList(navController: NavController) {

    val listOfBooks = listOf(
        MBook("dasd", "dfsdasd", "dsadas", null),
        MBook("dagsd", "dsdfhasd", "dsadas", null),
        MBook("dagsd", "dabcvbwsd", "dsadas", null),
        MBook("dasdd", "darwersd", "dsadas", null),
        MBook("dawsd", "datwesd", "dsadas", null),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = listOfBooks) {
            BookRow(it, navController)
        }
    }
}

@Composable
fun BookRow(
    book: MBook,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .height(100.dp)
            .padding(3.dp),
        shape = RectangleShape,
        elevation = 7.dp,
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            val imageUrl = "https://icdn.dantri.com.vn/thumb_w/640/2017/1-1510967806416.jpg"

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .height(120.dp)
                    .width(120.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Column() {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(
                    text = "Author: ${book.author}",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.caption
                )

            }
        }
    }
}
