package com.example.areader.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.areader.components.*
import com.example.areader.model.MBook
import com.example.areader.navigation.ReaderScreens

@Preview
@Composable
fun HomeScreen(navController: NavController = NavController(context = LocalContext.current)) {
    Scaffold(topBar = {
        ReaderHomeAppBar(
            title = "A.Reader",
            navController = navController
        )
    },
        floatingActionButton = {
            FABContent(onTab = {
                navController.popBackStack()
                navController.navigate(ReaderScreens.SearchScreen.name)
            })
        }) {
        Surface(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {

    val listOfBooks = listOf(
        MBook("dasd", "dfsdasd", "dsadas", null),
        MBook("dagsd", "dsdfhasd", "dsadas", null),
        MBook("dagsd", "dabcvbwsd", "dsadas", null),
        MBook("dasdd", "darwersd", "dsadas", null),
        MBook("dawsd", "datwesd", "dsadas", null),
    )

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleSelection(label = "Your reading\nactivities right now ")

            UserProfile("Long", navController)
        }

        ReadingRightNowArea(books = listOf(), navController = navController)

        BookListArea(listOfBooks = listOfBooks, navController = NavController)
    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController.Companion) {

    HorizontalScrollAbleComponent(listOfBooks){
        // TO DO: go to details screen
    }
}

@Composable
fun HorizontalScrollAbleComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(280.dp)
        .horizontalScroll(scrollState)) {
        for(book in listOfBooks) {
            ListCard(){
                onCardPressed(it)
            }
        }

    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard()
}

@Composable
fun ListCard(
    book: MBook = MBook("sdf", "fsd", "fsfsd", "fsdfsdf"),
    onPressDetails: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(
        shape = RoundedCornerShape(30.dp),
        elevation = 6.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressDetails.invoke(book.title.toString()) }
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = "https://icdn.dantri.com.vn/thumb_w/640/2017/1-1510967806416.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(120.dp)
                        .width(120.dp)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(height = 30.dp, width = 30.dp)
                    )

                    BookRating(score = 3.5)
                }
            }

            Text(
                text = book.title.toString(),
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 17.sp
            )

            Text(
                text = "Authors: ${book.author.toString()}",
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption
            )


        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            RoundedButton(label = "Reading", radius = 90)
        }

    }

}



