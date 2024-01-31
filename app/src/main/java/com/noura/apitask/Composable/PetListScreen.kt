package com.noura.apitask.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.noura.apitask.ViewModel.PetsViewModel


@Composable
fun PetListScreen(
    modifier: Modifier = Modifier,
    viewModel: PetsViewModel = viewModel()){
    val pets = viewModel.pets
    LazyColumn (modifier = modifier){
        items(pets) { pets ->
            PetItem(pets )
        }
    }
}