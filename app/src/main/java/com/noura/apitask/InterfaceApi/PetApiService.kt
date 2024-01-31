package com.noura.apitask.InterfaceApi

import com.noura.apitask.Model.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetApiService {
    @GET("Pet")
    suspend fun getAllPets(): List<Pet>

    @POST("Pet")
    suspend fun addPetApi(@Body pet: Pet): Response<Pet>

    @DELETE("Pet/{petID}")
    suspend fun deletePetApi(@Path("petID") petID: Int): Response<Unit>
}