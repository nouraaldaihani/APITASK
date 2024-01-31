package com.noura.apitask.repo

import com.noura.apitask.InterfaceApi.PetApiService

class PetRepo(private val api: PetApiService) {
    suspend fun getAllPets()= api.getAllPets()

}