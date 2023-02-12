package com.davidgllez.jetpackcompose

import FakeDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
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
    LazyColumn {
        items(allFilms.size) {
            //ItemListBasic(title = allFilms[it].name)
            ItemListClick(title = allFilms[it].name, modifier = Modifier.clickable {

            })
            Divider()
        }
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