package uk.org.rivernile.android.a50petitiontracker.repositories.petition

import retrofit2.Call
import retrofit2.http.GET

interface PetitionService {

    @GET("petitions/241584/count.json")
    fun getPetitionCount(): Call<PetitionCount>
}