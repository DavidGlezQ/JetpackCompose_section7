package com.davidgllez.jetpackcompose

import FakeDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.davidgllez.jetpackcompose.ui.theme.JetpackComposeSection7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSection7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //ListBasic(FakeDatabase.getAllFilms())
                    ListAdvance(FakeDatabase.getAllFilms())
                }
            }
        }
    }
}
@Composable
fun ListAdvance(allFilms: List<Film>) {
    //Actual context
    val context = LocalContext.current
    LazyColumn {
        items(allFilms.size) {
            //ItemListBasic(title = allFilms[it].name)
            //Click Method
            /*ItemListClick(title = allFilms[it].name, modifier = Modifier.clickable {
                Toast.makeText(context, "Click in ${allFilms[it].name}",
                    Toast.LENGTH_SHORT).show()
            })
            Divider()*/
            val film = allFilms[it]
            ItemListAdvance(film = film, modifier = Modifier.clickable {
                Toast.makeText(context, "Click in ${allFilms[it].name}",
                    Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ItemListAdvance(film: Film, modifier: Modifier) {
    Column(modifier = modifier) {
        ListItem(text = { Text(text = film.name, style = MaterialTheme.typography.h6) },
            secondaryText = { Text(text = film.description,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis) },
            singleLineSecondaryText = false, //Hacer que todos los items sean de tamaños iguales
            icon = {
                GlideImage(
                    model = film.photoURL, contentDescription = "Cover Film",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(dimensionResource(id = R.dimen.list_item_img_size))
                        .clip(
                            CircleShape
                        )
                        .border(BorderStroke(
                            dimensionResource(id = R.dimen.list_item_img_stroke), color = Color.Blue
                        ), CircleShape))
                //Icon(imageVector = Icons.Filled.Star, contentDescription = null)
            }
        )
        Divider()
    }
}
@Composable
fun ItemListClick(title: String, modifier: Modifier) {
    Text(text = title, modifier = modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.common_padding_default)),
        style = MaterialTheme.typography.h6)
}

@Composable
fun ListBasic(films: List<Film>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        films.forEach { ItemListBasic(title = it.name) }
    }
}

@Composable
fun ItemListBasic(title: String) {
    Text(text = title, modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.common_padding_default)),
        style = MaterialTheme.typography.h6)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeSection7Theme {
        ListBasic(FakeDatabase.getAllFilms())
    }
}