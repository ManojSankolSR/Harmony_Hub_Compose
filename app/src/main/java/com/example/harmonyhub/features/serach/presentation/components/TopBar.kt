package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModel
import com.example.harmonyhub.ui.theme.PermanentMarker


@Composable
fun TopBar(query: String, searchViewModel: SearchViewModel, focusManager: FocusManager) {
    Column(Modifier.padding(16.dp)) {
        Text(
            "Search",
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = PermanentMarker),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SearchBar(
            query = query,
            onQueryChange = {
                searchViewModel.updateQuery(it)
            },
            onSearch = {
                focusManager.clearFocus()
                searchViewModel.search(query)
            },
            onClear = {  searchViewModel.updateQuery("") }
        )
    }

}