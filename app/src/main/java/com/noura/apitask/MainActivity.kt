package com.noura.apitask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noura.apitask.Composable.PetItem
import com.noura.apitask.Model.Pet
import com.noura.apitask.ViewModel.PetsViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            APITaskTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                    AppContent()
//                }
//            }
//        }
//    }




    @Composable
fun PetListScreen(modifier: Modifier = Modifier, viewModel: PetsViewModel = viewModel()) {
    val pets = viewModel.pets
    LazyColumn {
        items(pets) {  pets ->
            PetItem(pets )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("")
@Composable
fun AppContent(PetsViewModel: PetsViewModel = viewModel()) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text("My pet List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addPet") }) {
                Text("+")
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = "petList") {
            composable("petList") {
                // Ensure BookListScreen is correctly implemented and contains visible content
                PetListScreen(Modifier.padding(padding))
            }
            composable("addPet") {
                AddPetsScreen()


            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetsScreen(modifier: Modifier = Modifier, PetsViewModel: PetsViewModel = viewModel()) {


    // State variables for input fields
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var adopted by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add a New Pet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = id,
                onValueChange = { id = it },
                label = "id"
            )
            InputField(
                value = name,
                onValueChange = { name = it },
                label = "name"
            )
            InputField(
                value = adopted,
                onValueChange = { adopted = it },
                label = "adopted",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = age,
                onValueChange = { age = it },
                label = "age",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = gender,
                onValueChange = { gender = it },
                label = "gender",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = "Image URL"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newPet = Pet(
                        id = 0,  // Assuming ID is generated by the server
                        name = name,
                        adopted = adopted,
                        age = age,
                        gender = gender,
                        image = imageUrl
                    )
                    PetsViewModel.addPet(newPet)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Book")
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}




