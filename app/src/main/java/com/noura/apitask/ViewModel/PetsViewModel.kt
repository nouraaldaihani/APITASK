package com.noura.apitask.ViewModel


import com.noura.apitask.repo.PetRepo
import com.noura.apitask.Model.Pet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noura.apitask.InterfaceApi.PetApiService
import com.noura.apitask.Singleton.RetroFitHelper

import kotlinx.coroutines.launch
import retrofit2.create

class PetsViewModel : ViewModel() {

    private val PetsApiService = RetroFitHelper.getInstance().create(PetApiService::class.java)
    private val repository = PetRepo(PetsApiService)


    var pets by mutableStateOf(listOf<Pet>())

    init {
        fetchPets()
    }

    private fun fetchPets() {
        viewModelScope.launch {
            try {
                pets = repository.getAllPets()
            } catch (e: Exception) {
            }
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            try {
                var response = PetsApiService.addPetApi(pet)

                if (response.isSuccessful && response.body() != null) {
                } else {

                }
            } catch (e: Exception) {
            }
        }
    }

    fun deletePet(petID: Int) {
        viewModelScope.launch {
            try {
                val response = PetsApiService.deletePetApi(petID)
                if (response.isSuccessful) {
                }else{
                }
            } catch (e: Exception) {

            }
        }
    }
}

